package com.prince.security.controller;

import com.prince.security.AppTokenUtil;
import com.prince.security.exception.AppAuthenticationException;
import com.prince.security.model.AppAuthenticationRequest;
import com.prince.security.model.AppAuthenticationResponse;
import com.prince.security.model.UserModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class AppAuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private AuthenticationManager authenticationManager;

    private AppTokenUtil tokenUtil;

    private UserDetailsService userDetailsService;

    public AppAuthenticationRestController(AuthenticationManager authenticationManager, AppTokenUtil tokenUtil, @Qualifier("appUserDetailsService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AppAuthenticationRequest authenticationRequest) {
        System.out.println(authenticationRequest.getUsername()+" "+authenticationRequest.getPassword());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = tokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AppAuthenticationResponse(token));
    }

    @GetMapping(value = "/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = tokenUtil.getUsernameFromToken(token);
        UserModel user = (UserModel) userDetailsService.loadUserByUsername(username);
        if(tokenUtil.validateToken(token, user) && tokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtil.refreshToken(token);
            return ResponseEntity.ok(new AppAuthenticationResponse(refreshedToken));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @ExceptionHandler({AppAuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AppAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AppAuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AppAuthenticationException("Bad credential!", e);
        }
    }
}

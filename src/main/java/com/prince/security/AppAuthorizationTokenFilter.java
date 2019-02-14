package com.prince.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AppAuthorizationTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserDetailsService userDetailsService;
    private final AppTokenUtil appTokenUtil;
    private final String tokenHeader;

    public AppAuthorizationTokenFilter(@Qualifier("appUserDetailsService") UserDetailsService userDetailsService, AppTokenUtil appTokenUtil, @Value("${jwt.header}") String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.appTokenUtil = appTokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("processing authentication for '{}'", httpServletRequest.getRequestURL());

        final String requestHeader = httpServletRequest.getHeader(this.tokenHeader);
        String username = null;
        String authToken = null;

        if(requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                username = appTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occurred during getting username from token ", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore ", e);
            }
        } else {
            logger.warn("couldn't find bearer string will ignore the header");
        }

        logger.info("checking authentication for user '{}'", username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.info("security context was null so authenticating user");

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if(appTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("user '{}' authenticated, setting security context", username);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

package com.prince.security.service;

import com.prince.security.UserDetailHelper;
import com.prince.security.entity.User;
import com.prince.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(!userOptional.isPresent())
            throw new UsernameNotFoundException("Couldn't find user "+ username);
        return UserDetailHelper.create(userOptional.get());
    }

    public String getUsernameByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(!userOptional.isPresent())
            throw new UsernameNotFoundException(email + " not registered.");
        return userOptional.get().getUsername();
    }
}

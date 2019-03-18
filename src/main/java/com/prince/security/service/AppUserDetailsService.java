package com.prince.security.service;

import com.prince.security.UserDetailHelper;
import com.prince.security.entity.User;
import com.prince.security.model.UserCommand;
import com.prince.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public UserCommand getUserCommandByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(!userOptional.isPresent())
            throw new UsernameNotFoundException("Couldn't find user "+ username);
        return UserDetailHelper.convertUserToCommand(userOptional.get());
    }

    public UserCommand updateUserCommandByUsername(String username, UserCommand userCommand) {
        User user = UserDetailHelper.convertUserCommandToUser(userCommand);
        Optional<User> userOptional = userRepository.findByUsername(username);
        user.setId(userOptional.get().getId());
        user = userRepository.save(user);
        return UserDetailHelper.convertUserToCommand(user);
    }

    public UserCommand addUser(UserCommand userCommand) {
        User user = UserDetailHelper.convertUserCommandToUser(userCommand);
        Optional<User> userOptional = userRepository.findByUsername(userCommand.getUsername());
        if(userOptional.isPresent())
            throw new UsernameNotFoundException("Username: "+ userCommand.getUsername() +"already exists, please try with different username.");
        userOptional = userRepository.findByEmail(userCommand.getEmail());
        if(userOptional.isPresent())
            throw new UsernameNotFoundException("Email id: "+ userCommand.getEmail() +"already registered.");
        user = userRepository.save(user);
        return UserDetailHelper.convertUserToCommand(user);
    }
    
    public List<UserCommand> getAllUsers() {
    	List<User> userList = userRepository.findAll();
    	return userList.stream().map(user -> UserDetailHelper.convertUserToCommand(user)).collect(Collectors.toList());
    }
}

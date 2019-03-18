package com.prince.security;

import com.prince.security.entity.Authority;
import com.prince.security.entity.AuthorityName;
import com.prince.security.entity.User;
import com.prince.security.model.UserCommand;
import com.prince.security.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailHelper {

    public static UserModel create(User user) {
        return new UserModel(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getEnabled(), user.getLastPasswordResetDate(),
                mapToGrantedAuthorities(user.getAuthorities()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }

    public static UserCommand convertUserToCommand(User user) {
        return new UserCommand(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getEnabled(), user.getAuthorities().stream().map(authority -> authority.getName().name())
                        .collect(Collectors.toList()));
    }

    public static User convertUserCommandToUser(UserCommand userCommand) {
        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setFirstName(userCommand.getFirstName());
        user.setEmail(userCommand.getEmail());
        user.setEnabled(userCommand.getEnabled());
        user.setLastName(userCommand.getLastName());
        user.setAuthorities(userCommand.getAuthorities().stream()
                .map(auth -> {
                    Authority authority = new Authority();
                    authority.setName(AuthorityName.valueOf(auth));
                    return authority;
                }).collect(Collectors.toList()));
        return user;
    }
}

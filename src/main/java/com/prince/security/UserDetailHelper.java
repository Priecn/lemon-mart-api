package com.prince.security;

import com.prince.security.entity.Authority;
import com.prince.security.entity.User;
import com.prince.security.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class UserDetailHelper {

    public static UserModel create(User user) {
        return new UserModel(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getEnabled(), user.getLastPasswordResetDate(), mapToGrantedAuthorities(user.getAuthorities()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                        .collect(Collectors.toList());
    }
}

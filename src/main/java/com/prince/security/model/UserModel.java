package com.prince.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class UserModel implements UserDetails {

    @JsonIgnore
    private static final long serialVersionUID = -3536766059739347616L;
    
    @JsonIgnore
    private final Long id;
    private final String username;
    @JsonIgnore
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Boolean enabled;
    @JsonIgnore
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserModel(Long id, String username, String password, String firstName, String lastName, String email, Boolean enabled, Date lastPasswordResetDate, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

package com.prince.security.model;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserCommand implements Serializable{
    @JsonIgnore
    private static final long serialVersionUID = -3536766059739347616L;  
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private List<String> authorities;

    public UserCommand(String username, String firstName, String lastName, String email, Boolean enabled, List<String> authorities) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.authorities = authorities;
    }
    
	public UserCommand() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
    
}
package com.prince.security.model;

import java.io.Serializable;

public class AppAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -1335484154532321545L;

    private final String token;

    public AppAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

package com.dev.codingchallenge.userportal.payload.response;

import com.dev.codingchallenge.userportal.entity.ApplicationUser;

public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private ApplicationUser user;

    public JwtResponse(String accessToken, ApplicationUser user) {
        this.token = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }
}

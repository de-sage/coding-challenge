package com.dev.codingchallenge.userportal.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ERole {

    ROLE_USER("USER"),
    ROLE_MODERATOR("ADMIN"),
    ROLE_ADMIN("SUPER_ADMIN");

//    ROLE_USER,
//    ROLE_MODERATOR,
//    ROLE_ADMIN

    public final String role;

    ERole(String status){
        this.role = status;
    }

    @JsonValue
    public String getStatus() {
        return role;
    }
}

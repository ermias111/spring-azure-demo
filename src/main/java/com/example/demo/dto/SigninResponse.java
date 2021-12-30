package com.example.demo.dto;

public class SigninResponse {
    private final String jwt;

    public SigninResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}

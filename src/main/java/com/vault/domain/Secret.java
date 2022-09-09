package com.vault.domain;

import java.security.SecureRandom;
import java.util.Base64;

import com.vault.domain.dtos.SecretDTO;

import lombok.Getter;

public class Secret {
    @Getter
    private String token;
    private String data;

    public Secret() {
        this.generateToken();
    }

    public Secret(String data) {
        this.data = data;
        this.generateToken();
    }

    public Secret(String token, String data) {
        this.token = token;
        this.data = data;
    }

    public Secret(SecretDTO secretDTO) {
        this.token = secretDTO.getToken();
        this.data = secretDTO.getData();
    }

    public String getData(String token) throws Exception {
        if (this.validate(token))
            return this.data;
        throw new Exception("Invalid token");
    }

    public void setData(String token, String data) throws Exception {
        if (this.validate(token))
            this.data = data;
        else
            throw new Exception("Invalid token");
    }

    private void generateToken() {
        var secureRandom = new SecureRandom();
        var base64encoder = Base64.getUrlEncoder();
        var randonBytes = new byte[32];
        secureRandom.nextBytes(randonBytes);
        this.token = base64encoder.encodeToString(randonBytes);
    }

    public boolean validate(String token) {
        return this.token == token;
    }

    public SecretDTO toDTO() {
        return new SecretDTO(this.token, this.data);
    }
}

package com.vault.domain.dtos;

import lombok.Getter;

public class SecretDTO {
    @Getter
    String token;
    @Getter
    String data;

    public SecretDTO(String token, String data) {
        this.token = token;
        this.data = data;
    }
}

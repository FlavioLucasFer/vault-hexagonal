package com.vault.infra.adapters.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.vault.domain.Secret;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "secrets")
public class SecretEntity {
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String data;

    public SecretEntity() {}

    public SecretEntity(Secret secret) throws Exception {
        this.token = secret.getToken();
        this.data = secret.getData(this.token);
    }

    public Secret toSecret() {
        return new Secret(this.token, this.data);
    }
}

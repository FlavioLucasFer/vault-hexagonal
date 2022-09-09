package com.vault.domain.ports.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.vault.domain.dtos.SecretDTO;

public interface SecretServicePort {
    SecretDTO getSecret(String token);
    SecretDTO createSecret(String data) throws Exception;
    SecretDTO updateSecret(String token, String data) throws NotFoundException, Exception;
    void deleteSecret(String token) throws NotFoundException, Exception;
}

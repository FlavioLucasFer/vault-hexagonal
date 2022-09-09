package com.vault.application.adapters.services;

import java.util.Objects;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.vault.domain.Secret;
import com.vault.domain.dtos.SecretDTO;
import com.vault.domain.ports.repositories.SecretRepositoryPort;
import com.vault.domain.ports.services.SecretServicePort;

public class SecretService implements SecretServicePort {
    private final SecretRepositoryPort secretRepository;

    public SecretService(SecretRepositoryPort secretRepository) {
        this.secretRepository = secretRepository;
    }

    @Override
    public SecretDTO getSecret(String token) {
        return this.secretRepository.findByToken(token).toDTO();
    }

    @Override
    public SecretDTO createSecret(String data) throws Exception {
        return this.secretRepository.save(new Secret(data)).toDTO();
    }

    @Override
    public SecretDTO updateSecret(String token, String data) throws NotFoundException, Exception {
        var secret = this.find(token);
        secret.setData(token, data);
        return this.secretRepository.save(secret).toDTO();
    }

    @Override
    public void deleteSecret(String token) throws NotFoundException, Exception {
        this.find(token);
        this.secretRepository.delete(token);
        
    }

    private Secret find(String token) throws NotFoundException {
        var secret = secretRepository.findByToken(token);

        if (Objects.isNull(secret))
            throw new NotFoundException();
        
        return secret;
    }
}

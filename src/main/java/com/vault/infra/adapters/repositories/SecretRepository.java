package com.vault.infra.adapters.repositories;

import java.util.Objects;
import java.util.Optional;

import com.vault.domain.Secret;
import com.vault.domain.ports.repositories.SecretRepositoryPort;
import com.vault.infra.adapters.entities.SecretEntity;

public class SecretRepository implements SecretRepositoryPort {
    private final SpringSecretRepository springSecretRepository;

    public SecretRepository(SpringSecretRepository springSecretRepository) {
        this.springSecretRepository = springSecretRepository;
    }

    @Override
    public Secret findByToken(String token) {
        Optional<SecretEntity> secretEntity = this.springSecretRepository.findByToken(token);

        if (secretEntity.isPresent())
            return secretEntity.get().toSecret();

        throw new RuntimeException("Secret not found");
    }

    @Override
    public Secret save(Secret secret) throws Exception {
        SecretEntity secretEntity;
        if (Objects.isNull(secret.getToken())) {
            secretEntity = new SecretEntity(secret);
        }
        else {
            secretEntity = this.springSecretRepository.findByToken(secret.getToken()).get();
            secretEntity.setData(secret.getData(secret.getToken()));
        }
        springSecretRepository.save(secretEntity);
        return secretEntity.toSecret();
    }

    @Override
    public void delete(String token) {
        this.springSecretRepository.deleteByToken(token);
    }
}

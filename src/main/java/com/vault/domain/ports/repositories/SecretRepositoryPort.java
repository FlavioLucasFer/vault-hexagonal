package com.vault.domain.ports.repositories;

import com.vault.domain.Secret;

public interface SecretRepositoryPort {
    Secret findByToken(String token);
    Secret save(Secret secret) throws Exception;
    void delete(String token);
}

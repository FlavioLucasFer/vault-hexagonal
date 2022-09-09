package com.vault.infra.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vault.infra.adapters.entities.SecretEntity;

@Repository
public interface SpringSecretRepository extends JpaRepository<SecretEntity, Integer> {
    Optional<SecretEntity> findByToken(String token);
    void deleteByToken(String token);
}

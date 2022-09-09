package com.vault.application.adapters.controllers;

import java.util.Objects;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vault.domain.dtos.SecretDTO;
import com.vault.domain.ports.services.SecretServicePort;

@RestController
@RequestMapping("secret")
public class SecretController {
    private final SecretServicePort secretServicePort;

    public SecretController(SecretServicePort secretServicePort) {
        this.secretServicePort = secretServicePort;
    }

    @GetMapping
    SecretDTO getSecret(@RequestHeader("token") String token) {
        if (Objects.isNull(token) || token.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        
        return this.secretServicePort.getSecret(token);
    }

    @PostMapping
    void createSecret(@RequestBody String data) {
        if (Objects.isNull(data) || data.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        try {
            this.secretServicePort.createSecret(data);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping
    SecretDTO updateSecret(@RequestHeader("token") String token, @RequestBody String data) {
        if (Objects.isNull(token) || token.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        
        try {
            return this.secretServicePort.updateSecret(token, data);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    void deleteSecret(@RequestHeader("token") String token) {
        if (Objects.isNull(token) || token.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        try {
            this.secretServicePort.deleteSecret(token);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}

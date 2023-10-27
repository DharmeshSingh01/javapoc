package com.example.emp.controller;

import java.util.NoSuchElementException;

import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

public interface KeyVaultClient {
	SecretClient getSecretClient();

    default KeyVaultSecret getSecret(String key) {
        KeyVaultSecret secret;
        try {
            secret = getSecretClient().getSecret(key);
        } catch (Exception ex) {
            throw new NoSuchElementException("Unable to retrieve %s secret".formatted(key), ex);
        }
        return secret;
    }
}

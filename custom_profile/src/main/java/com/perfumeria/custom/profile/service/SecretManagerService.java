package com.perfumeria.custom.profile.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfumeria.custom.profile.utils.AbstractCommonProperties;

import org.springframework.stereotype.Service;




//@Service
public class SecretManagerService extends AbstractCommonProperties{

    private final SecretsManagerClient secretsClient;

    public SecretManagerService() {
        this.secretsClient = SecretsManagerClient.builder()
                .region(Region.of("us-west-2")) // Cambia por tu región
                .build();
    }

    
    public JsonNode getSecret(String secretName) {
        GetSecretValueRequest request = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();
        
        GetSecretValueResponse response = secretsClient.getSecretValue(request);

        try {            
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.secretString());
        } catch (Exception e) {            
            setLog("recuperar secrero", 0L, "magento", "middleware", "No se pudo leer el secreto", null, ERROR_CONSTANT);
            
            throw new RuntimeException("No se pudo leer el secreto", e);

        }
    }
}

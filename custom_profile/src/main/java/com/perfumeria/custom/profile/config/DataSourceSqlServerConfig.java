package com.perfumeria.custom.profile.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.fasterxml.jackson.databind.JsonNode;
import com.perfumeria.custom.profile.service.SecretManagerService;

import org.springframework.beans.factory.annotation.Value;

//@Configuration
public class DataSourceSqlServerConfig {
    private final String AWS_SECRET;

    public DataSourceSqlServerConfig(@Value("${aws_secret}") String awsSecret) {
        this.AWS_SECRET = awsSecret;
    }

    @Autowired
    private SecretManagerService secretManagerService;

   // @Bean
   // @Primary
    public DataSource dataSourceCustom() {
        JsonNode secret = secretManagerService.getSecret(AWS_SECRET);

        String url = secret.get("url").asText();
        String username = secret.get("username").asText();
        String password = secret.get("password").asText();

       // System.out.println("******************=========================== " +username +"  " + password);
       
        return DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

}

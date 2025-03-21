package com.perfumeria.custom.profile.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.apache.hc.client5.http.config.RequestConfig;

//@Configuration
public class RestTemplateConfig {

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofSeconds(10)) // Tiempo máximo para establecer una conexión
                // Tiempo máximo para leer la respuesta (milisegundos)
                .build();

        // Crear un PoolingHttpClientConnectionManager para gestionar las conexiones

        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(100); // Máximo de conexiones
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(10); // Máximo de conexiones por ruta

        org.apache.hc.client5.http.impl.classic.CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(0);
        factory.setConnectionRequestTimeout(0);

        return factory;
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplateBuilder().requestFactory(this::clientHttpRequestFactory).build();
    }
}

package com.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Judge0Config {

    @Value("${judge0.api.url}")
    private String apiUrl;

    @Value("${judge0.api.key}")
    private String apiKey;

    @Value("${judge0.api.host}")
    private String apiHost;

    @Bean
    public RestClient judge0RestClient() {

        return RestClient.builder()
            .baseUrl(apiUrl)
            .defaultHeader("X-RapidAPI-Key", apiKey)
            .defaultHeader("X-RapidAPI-Host", apiHost)
            .defaultHeader("Content-Type", "application/json")
            .build();
    }
}

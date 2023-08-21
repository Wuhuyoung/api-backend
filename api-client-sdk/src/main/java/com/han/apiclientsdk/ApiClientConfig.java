package com.han.apiclientsdk;

import com.han.apiclientsdk.client.NameApiClient;
import com.han.apiclientsdk.client.SoulSootherClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "api.client")
@ComponentScan
public class ApiClientConfig {

    private String accessKey;
    private String secretKey;

    @Bean
    public NameApiClient nameApiClient() {
        return new NameApiClient(accessKey, secretKey);
    }

    @Bean
    public SoulSootherClient soulSootherClient() {
        return new SoulSootherClient(accessKey, secretKey);
    }
}

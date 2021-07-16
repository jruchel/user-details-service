package com.example.demo.configs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("filter-service")
@RequiredArgsConstructor
@Getter
@Setter
public class FilterServiceConfig {

    private String host, port;

}

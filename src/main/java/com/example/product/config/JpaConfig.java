package com.example.product.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.product.repository")
@EntityScan(basePackages = "com.example.product.domain")
public class JpaConfig {
}


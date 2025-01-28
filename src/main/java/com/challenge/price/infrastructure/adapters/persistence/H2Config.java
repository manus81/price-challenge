package com.challenge.price.infrastructure.adapters.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.challenge.price.infrastructure.adapters.persistence")
public class H2Config {
}
package com.example.stocksconsumer.config;

import com.example.stocksconsumer.ws.ApperateClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
public class ApperateConfigurator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApperateConfigurator.class);

    private final ApperateConfig apperateConfig;

    @Autowired
    public ApperateConfigurator(Environment environment) {
        this.apperateConfig = new ApperateConfig().readProps(environment, "apperate-client.");
    }


    @Bean("MSDClientApi3Impl")
    public ApperateClient createMSDClientApi3Impl() {
        final ApperateClient client  = new ApperateClient(apperateConfig);
        return client;
    }

}

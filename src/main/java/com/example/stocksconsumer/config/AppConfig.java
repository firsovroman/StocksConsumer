package com.example.stocksconsumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@PropertySource(value = "classpath:version.properties", encoding = "UTF-8")
@PropertySource(value = "classpath:apperate.properties", encoding = "UTF-8")
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public static final String APP_ID = "StocksConsumer";
    public static final String APP_TITLE = "StocksConsumer";

    private final String projectVersion;
    private final String buildProfile;
    private final String buildTimestamp;
    private final String title;

    Environment environment;

    @Autowired
    public AppConfig(Environment environment) {

        this.environment = environment;

        logger.info("Application starting: {} ({})", APP_ID, APP_TITLE);

        this.buildProfile = environment.getProperty("build_profile");
        logger.info("build_profile: {}", buildProfile);

        this.projectVersion = environment.getProperty("project_version");
        logger.info("project_version: {}", projectVersion);

        buildTimestamp = environment.getProperty("build_timestamp");
        logger.info("build_timestamp: {}", buildTimestamp);

        this.title = String.format("%s %s (buildProfile:%s, buildTimestamp:%s)",
                APP_TITLE, projectVersion, buildProfile, buildTimestamp);
    }

    public String getTitle() {
        return title;
    }

    @Bean
    public Environment configPropertyReader() {
        return this.environment;
    }

    @Bean("buildProfile")
    public String getBuildProfile() {
        return this.buildProfile;
    }


    @Override
    public String toString() {
        return "AppConfig{" +
                "projectVersion='" + projectVersion + '\'' +
                ", buildProfile='" + buildProfile + '\'' +
                ", buildTimestamp='" + buildTimestamp + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

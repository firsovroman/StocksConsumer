package com.example.stocksfetcher.config;

import org.springframework.core.env.Environment;

import java.util.Objects;

public class ApperateConfig {

    private String url;
    private String token;

    private boolean emulator;

    public ApperateConfig() {
    }

    protected ApperateConfig readProps(Environment reader, String keyPrefix) {
        this.url = Objects.requireNonNull(reader.getProperty(keyPrefix + "url"));
        this.token = Objects.requireNonNull(reader.getProperty(keyPrefix + "token"));

        this.emulator = Boolean.parseBoolean(Objects.requireNonNull(reader.getProperty(keyPrefix + "emulator")));

        return this;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public boolean isEmulator() {
        return emulator;
    }

    public void setEmulator(boolean emulator) {
        this.emulator = emulator;
    }


}

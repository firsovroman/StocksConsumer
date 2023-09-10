package com.example.stocksconsumer.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@PropertySource(value = "classpath:processor.properties", encoding = "UTF-8")
public class ProcessorConfig {

    private final String scheduleReaderIntervalMillis;

    public ProcessorConfig(@Qualifier("appEnvironment") Environment reader) {
        this.scheduleReaderIntervalMillis = Objects.requireNonNull(reader.getProperty("processor.scheduleReaderIntervalMillis"));
    }

    public String getScheduleReaderIntervalMillis() {
        return scheduleReaderIntervalMillis;
    }
}

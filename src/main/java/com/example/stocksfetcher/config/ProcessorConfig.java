package com.example.stocksfetcher.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@PropertySource(value = "classpath:processor.properties", encoding = "UTF-8")
public class ProcessorConfig {

    private final String scheduleCompaniesReaderIntervalMillis;
    private final String scheduleStocksReaderIntervalMillis;


    private final String scheduleReporterIntervalMillis;

    public ProcessorConfig(@Qualifier("appEnvironment") Environment reader) {
        this.scheduleCompaniesReaderIntervalMillis = Objects.requireNonNull(reader.getProperty("processor.scheduleCompaniesReaderIntervalMillis"));
        this.scheduleStocksReaderIntervalMillis = Objects.requireNonNull(reader.getProperty("processor.scheduleStocksReaderIntervalMillis"));
        this.scheduleReporterIntervalMillis = Objects.requireNonNull(reader.getProperty("processor.scheduleReporterIntervalMillis"));
    }

    public String getScheduleCompaniesReaderIntervalMillis() {
        return scheduleCompaniesReaderIntervalMillis;
    }

    public String getScheduleStocksReaderIntervalMillis() {
        return scheduleStocksReaderIntervalMillis;
    }

    public String getScheduleReporterIntervalMillis() {
        return scheduleReporterIntervalMillis;
    }
}

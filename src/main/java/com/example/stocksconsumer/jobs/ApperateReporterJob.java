package com.example.stocksconsumer.jobs;

import com.example.stocksconsumer.logic.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApperateReporterJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApperateReporterJob.class);

    private final Processor processor;

    public ApperateReporterJob(Processor processor) {
        this.processor = processor;
    }


    @Scheduled(initialDelayString = "10000", fixedRateString = "#{@processorConfig.scheduleReporterIntervalMillis}")
    public void job() {
        LOGGER.info("job() started");
        processor.report();
        LOGGER.info("job() completed");
    }

}

package com.example.stocksconsumer.jobs;

import com.example.stocksconsumer.config.ProcessorConfig;
import com.example.stocksconsumer.logic.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApperateReaderJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApperateReaderJob.class);

    private final Processor processor;
    private final ProcessorConfig processorConfig;

    public ApperateReaderJob(Processor processor, ProcessorConfig processorConfig) {
        this.processor = processor;
        this.processorConfig = processorConfig;
    }


    @Scheduled(initialDelayString = "5000", fixedRateString = "#{@processorConfig.scheduleReaderIntervalMillis}")
    public void job() {
        LOGGER.info("job() started");
//        processor.execute();
        LOGGER.info("job() completed");
    }

}

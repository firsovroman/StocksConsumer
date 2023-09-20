package com.example.stocksfetcher.jobs;

import com.example.stocksfetcher.logic.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApperateReporterJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApperateReporterJob.class);

    private final Processor processor;

    public ApperateReporterJob(Processor processor) {
        this.processor = processor;
    }


//    @Scheduled(initialDelayString = "20000", fixedRateString = "#{@processorConfig.scheduleReporterIntervalMillis}")
//    public void job() {
//        LOGGER.info("job() started");
//        processor.reportPayLoad();
//        LOGGER.info("job() completed");
//    }

}

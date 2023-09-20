package com.example.stocksfetcher.jobs;

import com.example.stocksfetcher.logic.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApperateReaderJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApperateReaderJob.class);

    private final Processor processor;

    public ApperateReaderJob(Processor processor) {
        this.processor = processor;
    }


//    @Scheduled(initialDelayString = "1000", fixedRateString = "#{@processorConfig.getScheduleCompaniesReaderIntervalMillis}")
//    public void jobCompanies() {
//        LOGGER.info("processCompanies() started");
//        processor.processCompanies();
//        LOGGER.info("processCompanies() completed");
//    }
//
//    @Scheduled(initialDelayString = "10000", fixedRateString = "#{@processorConfig.getScheduleStocksReaderIntervalMillis}")
//    public void jobStocks() {
//        LOGGER.info("processStocks() started");
//        processor.processStocks();
//        LOGGER.info("processStocks() completed");
//    }

}

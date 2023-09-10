package com.example.stocksconsumer.controller;

import com.example.stocksconsumer.config.AppConfig;
import com.example.stocksconsumer.entity.Companies;
import com.example.stocksconsumer.entity.Stock;
import com.example.stocksconsumer.logic.Processor;
import com.example.stocksconsumer.ws.ApperateClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    static final String TEXT_UTF8 = "text/plain;charset=utf-8";

    static final String JSON_UTF8 = "application/json;charset=utf-8";

    private final ApperateClient apperateClient;
    private final AppConfig appConfig;

    private final Processor processor;

    public TestController(ApperateClient apperateClient, AppConfig appConfig, Processor processor) {
        this.apperateClient = apperateClient;
        this.appConfig = appConfig;
        this.processor = processor;
    }

    @GetMapping(value = "/", produces = TEXT_UTF8)
    public ResponseEntity<String> getTitle() {
        return ResponseEntity.ok(appConfig.getTitle());
    }

    @GetMapping(value = "/company-test", produces = JSON_UTF8)
    public ResponseEntity<Companies> companyTest() {
        Companies result = apperateClient.companyRequest();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/stock-test", produces = JSON_UTF8)
    public ResponseEntity<Stock> stockTest() {
        Stock result = apperateClient.stockRequest("aapl");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/processor-test", produces = JSON_UTF8)
    public ResponseEntity<String> processorTest() {
        processor.execute();
        return new ResponseEntity<>("Ок", HttpStatus.OK);
    }
}

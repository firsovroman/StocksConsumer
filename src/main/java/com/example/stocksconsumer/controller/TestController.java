package com.example.stocksconsumer.controller;

import com.example.stocksconsumer.config.AppConfig;
import com.example.stocksconsumer.entity.Companies;
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

    public TestController(ApperateClient apperateClient, AppConfig appConfig) {
        this.apperateClient = apperateClient;
        this.appConfig = appConfig;
    }

    @GetMapping(value = "/", produces = TEXT_UTF8)
    public ResponseEntity<String> getTitle() {
        return ResponseEntity.ok(appConfig.getTitle());
    }

//    @GetMapping(value = "/api-test", produces = JSON_UTF8)
//    public ResponseEntity<String> apiTest() {
//        String result = apperateClient.makeGetRequest();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


    @GetMapping(value = "/api-test", produces = JSON_UTF8)
    public ResponseEntity<Companies> apiTest() {
        Companies result = apperateClient.makeGetRequest();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

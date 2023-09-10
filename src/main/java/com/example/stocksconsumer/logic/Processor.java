package com.example.stocksconsumer.logic;

import com.example.stocksconsumer.ws.ApperateClient;

public class Processor {

    private final ApperateClient client;


    public Processor(ApperateClient client) {
        this.client = client;
    }
}

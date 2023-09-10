package com.example.stocksconsumer.logic;

import com.example.stocksconsumer.entity.Companies;
import com.example.stocksconsumer.entity.Company;
import com.example.stocksconsumer.entity.Stock;
import com.example.stocksconsumer.ws.ApperateClient;
import org.springframework.stereotype.Component;

@Component
public class Processor {

    private final ApperateClient client;


    public Processor(ApperateClient client) {
        this.client = client;
    }


    public void execute() {
        Companies companies = client.companyRequest();
        for (Company c : companies) {
            Stock stock = client.stockRequest(c.getSymbol());
        }
    }

}

package com.example.stocksfetcher.models;

public class Company {

    private String symbol;

    private String exchange;

    private String exchangeSuffix;

    private String exchangeName;

    private String exchangeSegment;

    private String exchangeSegmentName;

    private String name;

    private String date;

    private String type;

    private String iexId;

    private String region;

    private String currency;

    private boolean isEnabled;

    private String figi;

    private String cik;

    private String lei;


    public Company() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchangeSuffix() {
        return exchangeSuffix;
    }

    public void setExchangeSuffix(String exchangeSuffix) {
        this.exchangeSuffix = exchangeSuffix;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeSegment() {
        return exchangeSegment;
    }

    public void setExchangeSegment(String exchangeSegment) {
        this.exchangeSegment = exchangeSegment;
    }

    public String getExchangeSegmentName() {
        return exchangeSegmentName;
    }

    public void setExchangeSegmentName(String exchangeSegmentName) {
        this.exchangeSegmentName = exchangeSegmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIexId() {
        return iexId;
    }

    public void setIexId(String iexId) {
        this.iexId = iexId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getFigi() {
        return figi;
    }

    public void setFigi(String figi) {
        this.figi = figi;
    }

    public String getCik() {
        return cik;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;
    }
}

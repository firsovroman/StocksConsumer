package com.example.stocksconsumer.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "stocks")
public class StockDTO {

    @Id
    @GeneratedValue(generator = "stock_stock_id_seq")
    private long stockId;
    private double change;
    private String companyName;
    private double latestPrice;
    private long previousVolume;
    private String symbol;
    private long volume;


    public StockDTO() {
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public long getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(long previousVolume) {
        this.previousVolume = previousVolume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "stockId=" + stockId +
                ", change=" + change +
                ", companyName='" + companyName + '\'' +
                ", latestPrice=" + latestPrice +
                ", previousVolume=" + previousVolume +
                ", symbol='" + symbol + '\'' +
                ", volume=" + volume +
                '}';
    }
}

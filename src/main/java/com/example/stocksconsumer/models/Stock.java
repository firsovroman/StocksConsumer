package com.example.stocksconsumer.models;

public class Stock {
    private long avgTotalVolume;
    private String calculationPrice;
    private double change;
    private double changePercent;
    private double close;
    private String closeSource;
    private long closeTime;
    private String companyName;
    private String currency;
    private double delayedPrice;
    private long delayedPriceTime;
    private double extendedChange;
    private double extendedChangePercent;
    private double extendedPrice;
    private long extendedPriceTime;
    private double high;
    private String highSource;
    private long highTime;
    private int iexAskPrice;
    private int iexAskSize;
    private int iexBidPrice;
    private int iexBidSize;
    private double iexClose;
    private long iexCloseTime;
    private long iexLastUpdated;
    private double iexMarketPercent;
    private double iexOpen;
    private long iexOpenTime;
    private double iexRealtimePrice;
    private int iexRealtimeSize;
    private int iexVolume;
    private long lastTradeTime;
    private double latestPrice;
    private String latestSource;
    private String latestTime;
    private long latestUpdate;
    private long latestVolume;
    private double low;
    private String lowSource;
    private long lowTime;
    private long marketCap;
    private double oddLotDelayedPrice;
    private long oddLotDelayedPriceTime;
    private double open;
    private long openTime;
    private String openSource;
    private double peRatio;
    private double previousClose;
    private long previousVolume;
    private String primaryExchange;
    private String symbol;
    private long volume;
    private double week52High;
    private double week52Low;
    private double ytdChange;
    private boolean isUSMarketOpen;

    public Stock() {
    }

    public long getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(long avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getCloseSource() {
        return closeSource;
    }

    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public double getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(double extendedChange) {
        this.extendedChange = extendedChange;
    }

    public double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public String getHighSource() {
        return highSource;
    }

    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    public long getHighTime() {
        return highTime;
    }

    public void setHighTime(long highTime) {
        this.highTime = highTime;
    }

    public int getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(int iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public int getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(int iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public int getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(int iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public int getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(int iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public double getIexClose() {
        return iexClose;
    }

    public void setIexClose(double iexClose) {
        this.iexClose = iexClose;
    }

    public long getIexCloseTime() {
        return iexCloseTime;
    }

    public void setIexCloseTime(long iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    public long getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public double getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public double getIexOpen() {
        return iexOpen;
    }

    public void setIexOpen(double iexOpen) {
        this.iexOpen = iexOpen;
    }

    public long getIexOpenTime() {
        return iexOpenTime;
    }

    public void setIexOpenTime(long iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    public double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public int getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(int iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public int getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(int iexVolume) {
        this.iexVolume = iexVolume;
    }

    public long getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(double latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public long getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public long getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(long latestVolume) {
        this.latestVolume = latestVolume;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getLowSource() {
        return lowSource;
    }

    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    public long getLowTime() {
        return lowTime;
    }

    public void setLowTime(long lowTime) {
        this.lowTime = lowTime;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    public void setOddLotDelayedPrice(double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    public long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    public void setOddLotDelayedPriceTime(long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public String getOpenSource() {
        return openSource;
    }

    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public long getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(long previousVolume) {
        this.previousVolume = previousVolume;
    }

    public String getPrimaryExchange() {
        return primaryExchange;
    }

    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
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

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public double getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }

    public boolean isUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setUSMarketOpen(boolean USMarketOpen) {
        isUSMarketOpen = USMarketOpen;
    }
}

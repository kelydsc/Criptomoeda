package br.com.criptomoeda.model.tickers;

import com.google.gson.annotations.SerializedName;

public class Tickers {

    //atributos
    private String symbol;

    private float frr;

    private float bid;

    @SerializedName("bid_period")
    private int bidPeriod;

    @SerializedName("bid_size")
    private float bidSize;

    private float ask;

    @SerializedName("ask_period")
    private int askPeriod;

    @SerializedName("ask_size")
    private float askSize;

    @SerializedName("daily_change")
    private float dailychange;

    @SerializedName("daily_change_relative")
    private float dailyChangeRelative;

    @SerializedName("last_price")
    private float lastPrice;

    private float volume;

    private float high;

    private float low;

    @SerializedName("frr_amount_available")
    private float frrAmountAvailable;

    //construtor
    public Tickers() {
    }

    //getters e setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getFrr() {
        return frr;
    }

    public void setFrr(float frr) {
        this.frr = frr;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public int getBidPeriod() {
        return bidPeriod;
    }

    public void setBidPeriod(int bidPeriod) {
        this.bidPeriod = bidPeriod;
    }

    public float getBidSize() {
        return bidSize;
    }

    public void setBidSize(float bidSize) {
        this.bidSize = bidSize;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public int getAskPeriod() {
        return askPeriod;
    }

    public void setAskPeriod(int askPeriod) {
        this.askPeriod = askPeriod;
    }

    public float getAskSize() {
        return askSize;
    }

    public void setAskSize(float askSize) {
        this.askSize = askSize;
    }

    public float getDailychange() {
        return dailychange;
    }

    public void setDailychange(float dailychange) {
        this.dailychange = dailychange;
    }

    public float getDailyChangeRelative() {
        return dailyChangeRelative;
    }

    public void setDailyChangeRelative(float dailyChangeRelative) {
        this.dailyChangeRelative = dailyChangeRelative;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getFrrAmountAvailable() {
        return frrAmountAvailable;
    }

    public void setFrrAmountAvailable(float frrAmountAvailable) {
        this.frrAmountAvailable = frrAmountAvailable;
    }
}

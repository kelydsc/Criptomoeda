package br.com.criptomoeda.model.ticker;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class Ticker {

    //atributos
    private float mid;

    private float bid;

    private float ask;

    @SerializedName("last_price")
    private float lastPrice;

    private float low;

    private float high;

    private float volume;

    private Time timestamp;

    //construtor
    public Ticker() {
    }

    //getter e setter
    public float getMid() {
        return mid;
    }

    public void setMid(float mid) {
        this.mid = mid;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public Time getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Time timestamp) {
        this.timestamp = timestamp;
    }
}

package br.com.criptomoeda.model.tickers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Ticker {

    @SerializedName("")
    private List<String> ticker = new ArrayList<>();

    public Ticker() {
    }

    public List<String> getTicker() {
        return ticker;
    }

    public void setTicker(List<String> ticker) {
        this.ticker = ticker;
    }
}


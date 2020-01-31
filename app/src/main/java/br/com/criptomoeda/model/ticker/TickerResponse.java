package br.com.criptomoeda.model.ticker;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TickerResponse {

    @SerializedName("")
    private List<String> tickers = new ArrayList<>();

    public TickerResponse() {
    }

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }
}

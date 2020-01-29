package br.com.criptomoeda.model.tickers;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TickersResponse {

    @SerializedName("")
    private List<Ticker> tickers = new ArrayList<>();

    public TickersResponse() {
    }

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }
}

package br.com.criptomoeda.model.symbol;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Symbol {

    @SerializedName("")
    private List<String> symbol = new ArrayList<>();

    public Symbol() {
    }

    public List<String> getSymbol() {
        return symbol;
    }

    public void setSymbol(List<String> symbol) {
        this.symbol = symbol;
    }
}


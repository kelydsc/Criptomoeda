package br.com.criptomoeda.model.global;

import br.com.criptomoeda.model.tickers.Tickers;

public class Global {

    private static Tickers tickers;

    public Global() {
    }

    public static Tickers getTickers() {
        return tickers;
    }

    public static void setTickers(Tickers tickers) {
        Global.tickers = tickers;
    }
}

package br.com.criptomoeda.repository;

import java.util.List;

import br.com.criptomoeda.data.network.ApiService;
import br.com.criptomoeda.model.ticker.Ticker;
import io.reactivex.Single;

public class TickerRepository {

    public Single<List<Ticker>> getTickerApi(String symbols) {
        return ApiService.getApiService().getTicker(symbols);
    }
}
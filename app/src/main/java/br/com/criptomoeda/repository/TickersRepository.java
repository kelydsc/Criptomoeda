package br.com.criptomoeda.repository;

import br.com.criptomoeda.data.network.ApiService;
import br.com.criptomoeda.model.tickers.TickersResponse;
import io.reactivex.Single;

public class TickersRepository {

    public Single<TickersResponse> getTickersApi(String symbols) {
        return ApiService.getApiService().getTickers(symbols);
    }
}

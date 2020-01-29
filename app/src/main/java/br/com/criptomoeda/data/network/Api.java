package br.com.criptomoeda.data.network;

import br.com.criptomoeda.model.tickers.TickersResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("tickers")
    Single<TickersResponse> getTickers(@Query("symbols") String symbols);
}

package br.com.criptomoeda.data.network;

import java.util.List;

import br.com.criptomoeda.model.symbol.Symbol;
import br.com.criptomoeda.model.ticker.Ticker;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("symbols")
    Single<List<Ticker>> getTicker(@Path("symbols") String symbols);

    @GET("symbols")
    Single<Symbol> getSymbol();
}

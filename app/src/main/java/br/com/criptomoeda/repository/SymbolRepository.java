package br.com.criptomoeda.repository;

import br.com.criptomoeda.data.network.ApiServiceSymbol;
import br.com.criptomoeda.model.symbol.Symbol;
import io.reactivex.Single;

public class SymbolRepository {

    public Single<Symbol> getSymbolApi() {
        return ApiServiceSymbol.getApiService().getSymbol();
    }
}

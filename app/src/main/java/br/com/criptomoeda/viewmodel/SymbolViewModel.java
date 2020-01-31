package br.com.criptomoeda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.criptomoeda.model.symbol.Symbol;
import br.com.criptomoeda.repository.SymbolRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.criptomoeda.util.Util.isNetworkConnected;

public class SymbolViewModel extends AndroidViewModel {

    private MutableLiveData<Symbol> symbolLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
    private SymbolRepository repository = new SymbolRepository();

    public SymbolViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Symbol> getSymbolLiveData() {
        return symbolLiveData;
    }

    public LiveData<Boolean> getSymbolLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getSymbolErrorLiveData() {
        return errorLiveData;
    }

    public void searchSymbol() {

        if (isNetworkConnected(getApplication())) {
            getApiSymbol();
        }
    }

    private void getApiSymbol() {

        disposable.add(
                repository.getSymbolApi()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(symbol -> symbolLiveData.setValue(symbol)
                                , throwable -> errorLiveData.setValue(throwable))
        );

    }

    // Limpa as chamadas no RX
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}

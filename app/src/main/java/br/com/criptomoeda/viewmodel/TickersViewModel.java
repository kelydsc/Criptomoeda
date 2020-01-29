package br.com.criptomoeda.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.criptomoeda.model.tickers.Tickers;
import br.com.criptomoeda.model.tickers.TickersResponse;
import br.com.criptomoeda.repository.TickersRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.criptomoeda.util.Util.isNetworkConnected;

public class TickersViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tickers>> tickersLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
    private TickersRepository repository = new TickersRepository();

    public TickersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Tickers>> getTickersLiveData() {
        return tickersLiveData;
    }

    public LiveData<Boolean> getTickersLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getTickersErrorLiveData() {
        return errorLiveData;
    }

    public void searchTickers(String symbols) {

        if (isNetworkConnected(getApplication())) {
            getApiTickers(symbols);
        }
    }

    private void getApiTickers(String symbols) {

        disposable.add(
                repository.getTickersApi(symbols)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(tickersResponse -> tickersLiveData.setValue(tickersResponse.getTickers())
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

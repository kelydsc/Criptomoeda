package br.com.criptomoeda.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.criptomoeda.model.ticker.Ticker;
import br.com.criptomoeda.repository.TickerRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.criptomoeda.util.Util.isNetworkConnected;

public class TickerViewModel extends AndroidViewModel {
    private MutableLiveData<List<Ticker>> tickerLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();
    private TickerRepository repository = new TickerRepository();

    public TickerViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Ticker>> getTickerLiveData() {
        return tickerLiveData;
    }

    public LiveData<Boolean> getTickerLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getTickerErrorLiveData() {
        return errorLiveData;
    }

    public void searchTicker(String symbols) {

        if (isNetworkConnected(getApplication())) {
            getApiTicker(symbols);
        }
    }

    private void getApiTicker(String symbols) {

        disposable.add(
                repository.getTickerApi(symbols)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(tickerList -> tickerLiveData.setValue(tickerList)
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

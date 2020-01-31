package br.com.criptomoeda.view.criptomoeda;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.criptomoeda.R;
import br.com.criptomoeda.adapters.RecyclerViewTickerAdapter;
import br.com.criptomoeda.interfaces.RecyclerViewTickerClickListener;
import br.com.criptomoeda.model.ticker.Ticker;
import br.com.criptomoeda.viewmodel.TickerViewModel;

public class TickerActivity extends AppCompatActivity implements RecyclerViewTickerClickListener {

    private RecyclerViewTickerAdapter adapter;
    private RecyclerView recyclerViewTicker;

    private List<Ticker> tickerList = new ArrayList<>();

    private TickerViewModel tickerViewModel;

    private ProgressBar progressBarBuscaTicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);

        //Inicializa Views
        initViews();

        //Busca dados da API atraves do retorno do Symbol
        tickerViewModel.searchTicker("btcusd");

        //Valida retorno da Api
        validaRetornoApiCriptomoeda();
    }

    private void validaRetornoApiCriptomoeda() {

        //Observable Loading
        tickerViewModel.getTickerLoadingLiveData().observe(TickerActivity.this, isLoading -> {
            if (isLoading) {
                progressBarBuscaTicker.setVisibility(View.VISIBLE);
            } else {
                progressBarBuscaTicker.setVisibility(View.GONE);
            }
        });

        tickerViewModel.getTickerLiveData().observe(TickerActivity.this, tickerListLocal -> {

            //Valida o retorno da API
            if (tickerListLocal.isEmpty()) {

                Toast.makeText(getApplicationContext(),
                        "Moeda não localizada!",
                        Toast.LENGTH_LONG).show();

            } else {

                //Retorna os dados do Ticker gravados na Api
                tickerList.addAll(tickerListLocal);

                adapter.updateTicker(tickerListLocal);
            }
        });

        //Observable Error
        tickerViewModel.getTickerErrorLiveData().observe(this, throwable -> {
            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {

        progressBarBuscaTicker = findViewById(R.id.progressBarBuscaTicker);

        //RecyclerView de Ticker
        recyclerViewTicker = findViewById(R.id.recyclerviewTicker);
        adapter = new RecyclerViewTickerAdapter(tickerList, this);

        recyclerViewTicker.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTicker.setAdapter(adapter);

        //Inicializa a classe tickerViewModel
        tickerViewModel = ViewModelProviders.of(this).get(TickerViewModel.class);
    }

    @Override
    public void onClick(Ticker ticker) {

    }
}

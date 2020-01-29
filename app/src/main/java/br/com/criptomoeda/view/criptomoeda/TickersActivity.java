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
import br.com.criptomoeda.adapters.RecyclerViewTickersAdapter;
import br.com.criptomoeda.interfaces.RecyclerViewTickersClickListener;
import br.com.criptomoeda.model.tickers.Tickers;
import br.com.criptomoeda.viewmodel.TickersViewModel;

public class TickersActivity extends AppCompatActivity implements RecyclerViewTickersClickListener {

    private RecyclerViewTickersAdapter adapter;
    private RecyclerView recyclerViewTickers;

    private List<Tickers> tickersList = new ArrayList<>();

    private TickersViewModel tickersViewModel;

    private ProgressBar progressBarBuscaTickers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickers);

        //Inicializa Views
        initViews();

        //Busca dados da API
        tickersViewModel.searchTickers("ALL");

        //Valida retorno da Api
        validaRetornoApiCriptomoeda();
    }

    private void validaRetornoApiCriptomoeda() {

        //Observable Loading
        tickersViewModel.getTickersLoadingLiveData().observe(this, isLoading -> {
            if (isLoading) {
                progressBarBuscaTickers.setVisibility(View.VISIBLE);
            } else {
                progressBarBuscaTickers.setVisibility(View.GONE);
            }
        });

        tickersViewModel.getTickersLiveData().observe(this, tickersListLocal -> {

            //Valida o retorno da API
            if (!tickersListLocal.isEmpty() && tickersListLocal != null) {

                //Retorna os dados do Tickers gravados na Api
                tickersList.addAll(tickersListLocal);

                adapter.updateTickers(tickersListLocal);

            } else {

                Toast.makeText(getApplicationContext(),
                        "Moeda não localizada!",
                        Toast.LENGTH_LONG).show();
            }
        });

        //Observable Error
        tickersViewModel.getTickersErrorLiveData().observe(this, throwable -> {
            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {

        progressBarBuscaTickers = findViewById(R.id.progressBarBuscaTickers);

        //RecyclerView de Tickers
        recyclerViewTickers = findViewById(R.id.recyclerviewTickers);
        adapter = new RecyclerViewTickersAdapter(tickersList,this);

        recyclerViewTickers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTickers.setAdapter(adapter);

        //Inicializa a classe tickersViewModel
        tickersViewModel = ViewModelProviders.of(this).get(TickersViewModel.class);
    }

    @Override
    public void onClick(Tickers tickers) {

    }
}

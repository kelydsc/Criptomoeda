package br.com.criptomoeda.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.criptomoeda.R;
import br.com.criptomoeda.adapters.RecyclerViewSymbolAdapter;
import br.com.criptomoeda.interfaces.RecyclerViewSymbolClickListener;
import br.com.criptomoeda.model.symbol.Symbol;
import br.com.criptomoeda.viewmodel.SymbolViewModel;

public class SymbolActivity extends AppCompatActivity implements RecyclerViewSymbolClickListener {

    private RecyclerViewSymbolAdapter adapter;
    private RecyclerView recyclerViewSymbol;

    private Symbol symbolList;

    private SymbolViewModel symbolViewModel;

    private ProgressBar progressBarBuscaSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbol);

        //Inicializa Views
        initViews();

        //Busca dados da API atraves do retorno do Symbol
        symbolViewModel.searchSymbol();

        //Valida retorno da Api
        validaRetornoApiCriptomoeda();
    }

    private void validaRetornoApiCriptomoeda() {

        //Observable Loading
        symbolViewModel.getSymbolLoadingLiveData().observe(SymbolActivity.this, isLoading -> {
            if (isLoading) {
                progressBarBuscaSymbol.setVisibility(View.VISIBLE);
            } else {
                progressBarBuscaSymbol.setVisibility(View.GONE);
            }
        });

        symbolViewModel.getSymbolLiveData().observe(SymbolActivity.this, symbolListLocal -> {

            //Valida o retorno da API
            if (symbolListLocal == null) {

                Toast.makeText(getApplicationContext(),
                        "Moeda não localizada!",
                        Toast.LENGTH_LONG).show();

            } else {

                //Retorna os dados do Symbol gravados na Api
                symbolList = symbolListLocal;

                adapter.updateSymbol(symbolList);
            }
        });

        //Observable Error
        symbolViewModel.getSymbolErrorLiveData().observe(this, throwable -> {
            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initViews() {

        progressBarBuscaSymbol = findViewById(R.id.progressBarBuscaSymbol);

        //RecyclerView de Symbol
        recyclerViewSymbol = findViewById(R.id.recyclerviewSymbol);
        adapter = new RecyclerViewSymbolAdapter(symbolList, this);

        recyclerViewSymbol.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSymbol.setAdapter(adapter);

        //Inicializa a classe symbolViewModel
        symbolViewModel = ViewModelProviders.of(this).get(SymbolViewModel.class);
    }

    @Override
    public void onClick(Symbol symbol) {

    }

}

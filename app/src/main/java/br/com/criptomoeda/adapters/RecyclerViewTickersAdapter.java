package br.com.criptomoeda.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.criptomoeda.R;
import br.com.criptomoeda.interfaces.RecyclerViewTickersClickListener;
import br.com.criptomoeda.model.tickers.Tickers;
import br.com.criptomoeda.util.Util;

public class RecyclerViewTickersAdapter extends RecyclerView.Adapter<RecyclerViewTickersAdapter.ViewHolder> {

    private List<Tickers> tickersList;
    private RecyclerViewTickersClickListener listener;

    public RecyclerViewTickersAdapter(List<Tickers> tickersList, RecyclerViewTickersClickListener listener) {
        this.tickersList = tickersList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewTickersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recyclerview_tickers_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTickersAdapter.ViewHolder viewHolder, int position) {

        final Tickers tickers = tickersList.get(position);

        viewHolder.bind(tickers);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(tickers);
            }
        });
    }

    //Método para limpar a pesquisa das criptomoedas
    public void clear() {
        this.tickersList.clear();
        notifyDataSetChanged();
    }

    //Método para atualizar o item
    public void updateTickers(List<Tickers> tickersList) {
        clear();

        this.tickersList = tickersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tickersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Declaração de atributos
        private ImageView imageViewRecyclerviewTickers;
        private TextView textViewRecyclerviewTickersSymbols;
        private TextView textViewRecyclerviewTickersLastPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewRecyclerviewTickers = itemView.findViewById(R.id.imageViewRecyclerviewTickers);
            textViewRecyclerviewTickersSymbols = itemView.findViewById(R.id.textViewRecyclerviewTickersSymbols);
            textViewRecyclerviewTickersLastPrice = itemView.findViewById(R.id.textViewRecyclerviewTickersLastPrice);
        }

        public void bind(Tickers tickers) {

            if (tickers.getSymbol() != null) {
                textViewRecyclerviewTickersSymbols.setText(tickers.getSymbol());
            } else {
                textViewRecyclerviewTickersSymbols.setText("Moeda não encontrada!");
            }

            if (tickers.getLastPrice() != 0) {

                //Aplica a formatacao de Valor na tela
                float valorCriptomoeda = 0;
                valorCriptomoeda = tickers.getLastPrice();

                String valorCampo = "";

                valorCampo = String.valueOf(valorCriptomoeda);

                //Aplica o formato de moeda na tela
                valorCampo = Util.trataFormatoMoeda(Float.valueOf(valorCampo));

                textViewRecyclerviewTickersLastPrice.setText(String.valueOf(valorCampo));
            } else {
                textViewRecyclerviewTickersLastPrice.setText("0,00");
            }
        }
    }
}

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
import br.com.criptomoeda.interfaces.RecyclerViewTickerClickListener;
import br.com.criptomoeda.model.ticker.Ticker;
import br.com.criptomoeda.util.Util;

public class RecyclerViewTickerAdapter extends RecyclerView.Adapter<RecyclerViewTickerAdapter.ViewHolder> {

    private List<Ticker> tickerList;
    private RecyclerViewTickerClickListener listener;

    public RecyclerViewTickerAdapter(List<Ticker> tickerList, RecyclerViewTickerClickListener listener) {
        this.tickerList = tickerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewTickerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recyclerview_ticker_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewTickerAdapter.ViewHolder viewHolder, int position) {

        final Ticker ticker = tickerList.get(position);

        viewHolder.bind(ticker);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(ticker);
            }
        });
    }

    //Método para limpar a pesquisa das criptomoedas
    public void clear() {
        this.tickerList.clear();
        notifyDataSetChanged();
    }

    //Método para atualizar o item
    public void updateTicker(List<Ticker> tickerList) {
        clear();

        this.tickerList = tickerList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tickerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Declaração de atributos
        //private ImageView imageViewRecyclerviewTicker;
        //private TextView textViewRecyclerviewTickerSymbols;
        private TextView textViewRecyclerviewTickerLastPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageViewRecyclerviewTicker = itemView.findViewById(R.id.imageViewRecyclerviewTicker);
           //textViewRecyclerviewTickerSymbols = itemView.findViewById(R.id.textViewRecyclerviewTickerSymbols);
            textViewRecyclerviewTickerLastPrice = itemView.findViewById(R.id.textViewRecyclerviewTickerLastPrice);
        }

        public void bind(Ticker ticker) {

            /*
            if (ticker.getSymbol() != null) {
                textViewRecyclerviewTickerSymbols.setText(ticker.getSymbol());
            } else {
                textViewRecyclerviewTickerSymbols.setText("Moeda não encontrada!");
            }
            */

            if (ticker.getLastPrice() != 0) {

                //Aplica a formatacao de Valor na tela
                float valorCriptomoeda = 0;
                valorCriptomoeda = ticker.getLastPrice();

                String valorCampo = "";

                valorCampo = String.valueOf(valorCriptomoeda);

                //Aplica o formato de moeda na tela
                valorCampo = Util.trataFormatoMoeda(Float.valueOf(valorCampo));

                textViewRecyclerviewTickerLastPrice.setText(String.valueOf(valorCampo));
            } else {
                textViewRecyclerviewTickerLastPrice.setText("0,00");
            }
        }
    }
}

package br.com.criptomoeda.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.criptomoeda.R;
import br.com.criptomoeda.interfaces.RecyclerViewSymbolClickListener;
import br.com.criptomoeda.model.symbol.Symbol;

public class RecyclerViewSymbolAdapter extends RecyclerView.Adapter<RecyclerViewSymbolAdapter.ViewHolder> {

    private Symbol symbolList;
    private RecyclerViewSymbolClickListener listener;

    public RecyclerViewSymbolAdapter(Symbol symbolList, RecyclerViewSymbolClickListener listener) {
        this.symbolList = symbolList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewSymbolAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.recyclerview_symbol_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewSymbolAdapter.ViewHolder viewHolder, int position) {

        final Symbol symbol = symbolList;

        viewHolder.bind(symbol);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(symbol);
            }
        });
    }

    @Override
    public int getItemCount() {
        //return symbolList;

        //return symbolList.getSymbol().size();
        return 0;
    }

    /*
    //Método para limpar a pesquisa das criptomoedas
    public void clear() {
        this.symbolList.clear();
        notifyDataSetChanged();
    }
    */

    //Método para atualizar o item
    public void updateSymbol(Symbol symbolList) {
        this.symbolList = symbolList;

        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //Declaração de atributos
        //private ImageView imageViewRecyclerviewTicker;
        private TextView textViewRecyclerviewSymbols;
        //private TextView textViewRecyclerviewTickerLastPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageViewRecyclerviewTicker = itemView.findViewById(R.id.imageViewRecyclerviewTicker);
            textViewRecyclerviewSymbols = itemView.findViewById(R.id.textViewRecyclerviewSymbols);
            //textViewRecyclerviewTickerLastPrice = itemView.findViewById(R.id.textViewRecyclerviewTickerLastPrice);
        }

        public void bind(Symbol symbol) {


            if (symbol.getSymbol() != null) {
                textViewRecyclerviewSymbols.setText(symbol.getSymbol().get(0));
            } else {
                textViewRecyclerviewSymbols.setText("Moeda não encontrada!");
            }

            /*

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
            */
        }
    }
}

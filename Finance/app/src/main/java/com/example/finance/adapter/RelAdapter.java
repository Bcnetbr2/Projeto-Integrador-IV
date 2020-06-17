package com.example.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finance.R;
import com.example.finance.conversor.ConverterData;
import com.example.finance.entidades.Lancamento;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RelAdapter extends ArrayAdapter<Lancamento> {

    private final Context context;
    private final List<Lancamento> elementos;
    private ConverterData cd = new ConverterData();


    public RelAdapter(Context context, List<Lancamento> elementos) {
        super(context, R.layout.linhaview,elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhaview, parent, false);
        TextView data = (TextView) rowView.findViewById(R.id.txtDataLc);
        TextView tipo = (TextView) rowView.findViewById(R.id.txtTipoLc);
        TextView valor = (TextView) rowView.findViewById(R.id.txtValorLc);

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        data.setText(cd.formataDataString2(elementos.get(position).getData()));
        tipo.setText(elementos.get(position).getFornecedor().getNome());
        valor.setText(formato.format(elementos.get(position).getValor()));


        return rowView;

    }
}

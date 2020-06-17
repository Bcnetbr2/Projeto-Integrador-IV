package com.example.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finance.R;
import com.example.finance.consulta.ObjetoConsultaCategoria;
import com.example.finance.consulta.ObjetoConsultaMes;
import com.example.finance.conversor.ConverterData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


public class CatAdapter extends ArrayAdapter<ObjetoConsultaCategoria> {

    private final Context context;
    private final List<ObjetoConsultaCategoria> elementos;
    private ConverterData cd = new ConverterData();


    public CatAdapter(Context context, List<ObjetoConsultaCategoria> elementos) {
        super(context, R.layout.consultaporcategoriaview,elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.consultaporcategoriaview, parent, false);
        TextView mes = (TextView) rowView.findViewById(R.id.txtMesCat);
        TextView valor = (TextView) rowView.findViewById(R.id.txtValorCat);
        TextView nome = (TextView) rowView.findViewById(R.id.txtNomeCat);

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        mes.setText(elementos.get(position).getMes());
        try {
            valor.setText(formato.format(elementos.get(position).getValor()));
        }catch(Exception e){

            valor.setText(String.valueOf(elementos.get(position).getValor()));

        }
        nome.setText(elementos.get(position).getNomeCategoria());

        return rowView;

    }

}

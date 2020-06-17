package com.example.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finance.R;
import com.example.finance.consulta.ObjetoConsultaMes;
import com.example.finance.conversor.ConverterData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class MesAdapter extends ArrayAdapter<ObjetoConsultaMes> {

    private final Context context;
    private final List<ObjetoConsultaMes> elementos;
    private ConverterData cd = new ConverterData();

    // criando uma lista com filtros de mes e ano
    public MesAdapter(Context context, List<ObjetoConsultaMes> elementos) {
        super(context, R.layout.consultamesanoview,elementos);
        this.context = context;
        this.elementos = elementos;
    }
    // colocando quais valores serão apresentados para o usuario com o filtro
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.consultamesanoview, parent, false);
        TextView mes = (TextView) rowView.findViewById(R.id.txtMes);
        TextView valor = (TextView) rowView.findViewById(R.id.txtValor);
        TextView tipo = (TextView) rowView.findViewById(R.id.txtTipo);

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        mes.setText(elementos.get(position).getMesAno());
        valor.setText(formato.format(elementos.get(position).getValorTotalGasto()));
        tipo.setText(elementos.get(position).getTipo());

        return rowView;

    }

}

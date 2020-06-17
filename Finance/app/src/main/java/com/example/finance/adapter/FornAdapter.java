package com.example.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.finance.R;
import com.example.finance.consulta.ObjetoConsultaCategoria;
import com.example.finance.consulta.ObjetoConsultaFornecedor;
import com.example.finance.consulta.ObjetoConsultaMes;
import com.example.finance.conversor.ConverterData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FornAdapter extends ArrayAdapter<ObjetoConsultaFornecedor> {

    private final Context context;
    private final List<ObjetoConsultaFornecedor> elementos;
    private ConverterData cd = new ConverterData();

    // criando uma lista com filtros por fornecedor
    public FornAdapter(Context context, List<ObjetoConsultaFornecedor> elementos) {
        super(context, R.layout.consultaporfornecedorview,elementos);
        this.context = context;
        this.elementos = elementos;
    }
    // colocando quais valores irão aparecer para o usuario
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.consultaporfornecedorview, parent, false);
        TextView mes = (TextView) rowView.findViewById(R.id.txtMesAno);
        TextView valor = (TextView) rowView.findViewById(R.id.txtValorForn);
        TextView tipo = (TextView) rowView.findViewById(R.id.txtNomeForn);

        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        mes.setText(elementos.get(position).getMes());
        valor.setText(formato.format(elementos.get(position).getValor()));
        tipo.setText(elementos.get(position).getNomeFornecedor());

        return rowView;

    }
}

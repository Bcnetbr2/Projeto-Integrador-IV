package com.example.finance.conversor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterData {

    public Date converterLongData(long dataRecebida){

        Date data = new Date(dataRecebida);

        return data;


    }

    public Date converterStringData(String dataString) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = formatar.parse(dataString);

        return data;

    }

    public String formataDataString(Date data){

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormat = formatar.format(data);

        return dataFormat;

    }

    public Long formataDataLong(Date data){

        long dataLong = data.getTime();

        return dataLong;
    }

}

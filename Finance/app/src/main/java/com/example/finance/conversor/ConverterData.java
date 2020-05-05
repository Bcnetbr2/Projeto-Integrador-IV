package com.example.finance.conversor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterData {

    public Date converter(long dataRecebida) throws ParseException {

        Date data = new Date(dataRecebida*1000);

        return data;


    }

}

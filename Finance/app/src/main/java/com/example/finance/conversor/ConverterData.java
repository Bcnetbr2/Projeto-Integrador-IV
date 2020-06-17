package com.example.finance.conversor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ConverterData {

    public Date converterLongData(long dataRecebida) {

        Date data = new Date(dataRecebida);

        return data;

    }

    public Date converterStringData(String dataString) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return formatar.parse(dataString);

    }

    public String formataDataString(Date data) {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormat = formatar.format(data);

        return dataFormat;

    }

    public String formataDataStringBusca(Date data) {

        SimpleDateFormat formatar = new SimpleDateFormat("MM/yyyy");
        String dataFormat = formatar.format(data);

        return dataFormat;

    }

    public Long formataDataLong(Date data) {

        long dataLong = data.getTime();

        return dataLong;
    }

    public Date DataInicial(Date data) {

        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(data);

        calendarData.add(Calendar.HOUR, 0);
        calendarData.add(Calendar.MINUTE, 0);
        calendarData.add(Calendar.SECOND, 0);


        int numeroDiasParaSubtrair = -7;


        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);
        Date dataInicial = calendarData.getTime();

        return dataInicial;

    }

    public Date gerarDataInicial(String dataString) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatar.parse(dataString);

        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(data);
        calendarData.add(Calendar.HOUR, 0);
        calendarData.add(Calendar.MINUTE, 0);
        calendarData.add(Calendar.SECOND, 0);

        Date dataFinal = calendarData.getTime();

        return dataFinal;

    }

    public Date gerarDataFinal(String dataString) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatar.parse(dataString);

        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(data);
        calendarData.add(Calendar.HOUR, 23);
        calendarData.add(Calendar.MINUTE, 59);
        calendarData.add(Calendar.SECOND, 59);

        Date dataFinal = calendarData.getTime();

        return dataFinal;

    }

    public String formataDataString2(Date data) {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormat = formatar.format(data);

        return dataFormat;

    }

    public Date gerarDataInicial2(String dataI) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatar.parse(dataI);

        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(data);
        calendarData.add(Calendar.HOUR, 0);
        calendarData.add(Calendar.MINUTE, 0);
        calendarData.add(Calendar.SECOND, 0);

        int numeroDiasParaSubtrair = -7;

        calendarData.add(Calendar.DATE, numeroDiasParaSubtrair);

        Date dataFinal = calendarData.getTime();

        return dataFinal;

    }

    public Date gerarDataFinal2(String dataF) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatar.parse(dataF);

        Calendar calendarData = Calendar.getInstance();
        calendarData.setTime(data);
        calendarData.add(Calendar.HOUR, 23);
        calendarData.add(Calendar.MINUTE, 59);
        calendarData.add(Calendar.SECOND, 59);

        Date dataFinal = calendarData.getTime();

        return dataFinal;

    }

    public String teste(Date data) throws ParseException {

        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormat = formatar.format(data);

        return dataFormat;

    }

    public Date converterStringData2(String dataString) throws ParseException {


        SimpleDateFormat formatar = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = formatar.parse(dataString);
        return d;

    }
}

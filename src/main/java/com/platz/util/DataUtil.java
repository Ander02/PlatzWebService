package com.platz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Anderson
 */
public class DataUtil {

    public String converterData(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return dateFormat.format(date);
        } else {
            return null;
        }
    }

    public Date converterData(String data) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return dateFormat.parse(data);
        } catch (ParseException ex) {
            System.out.println("Erro de Conversão: " + ex.getMessage());
            return null;
        }
    }

    public Date converterDataSemHora(String data) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(data);
        } catch (ParseException ex) {
            System.out.println("Erro de Conversão: " + ex.getMessage());
            return null;
        }
    }
}

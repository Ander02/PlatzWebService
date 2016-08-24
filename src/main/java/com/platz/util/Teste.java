package com.platz.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        Date dataProximaSemana = calendar.getTime();
        System.out.println(dataProximaSemana.toString());
    }
}

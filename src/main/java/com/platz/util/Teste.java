package com.platz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {

        Date d = new Date();
       if (d != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HHmmssSSS");
            System.out.println( dateFormat.format(d));
        } else {
            System.out.println("nada");
        }
    }
}

package com.platz.util;

import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {
        String dataString = "14/05/1999 20:50";
        
        Date dataDate = new DataUtil().converterData(dataString);
        
        System.out.println(dataDate.toString());
        
    }
}

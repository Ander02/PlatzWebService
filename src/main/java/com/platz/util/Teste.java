package com.platz.util;

import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {
        String dataString =new DataUtil().converterData(new Date());
        System.out.println(dataString);
        Date dataDate = new DataUtil().converterData(dataString);        
        System.out.println(dataDate.toString());
        
    }
}

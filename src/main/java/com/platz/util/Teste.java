package com.platz.util;

import java.util.Date;

/**
 *
 * @author Anderson
 */
public class Teste {

    public static void main(String[] args) {

        Date d = new Date();
        Date p = new DataUtil().converterData("12/12/2012 00:00");
        Date f = new DataUtil().converterData("12/12/2018 00:00");
        System.out.println(d.after(p));
        System.out.println(d.before(f));

    }
}

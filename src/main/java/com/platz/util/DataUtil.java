package com.platz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class DataUtil {

    public String converterData(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(date);
    }

}

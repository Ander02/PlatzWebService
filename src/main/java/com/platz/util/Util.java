package com.platz.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author Anderson
 */
public class Util {

    public String converterData(ObjectId id) {
        Date date = id.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(date);
    }

}

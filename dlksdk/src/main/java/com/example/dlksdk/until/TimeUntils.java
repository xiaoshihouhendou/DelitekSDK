package com.example.dlksdk.until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUntils {

    public static String TimeFormat() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return sdf1.format(date);
    }
}

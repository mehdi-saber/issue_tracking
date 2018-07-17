package com.atrosys.platform.util;

import java.util.Calendar;

/**
 * Created by Kasra on 3/15/2018.
 */
public class DateUtil {
    public static Calendar moveToDateFromCurrentDate(int day,int hour,int minutes){
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+day);
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+hour);
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+minutes);
        return calendar;
    }

    public static void main(String[] args) {
        moveToDateFromCurrentDate(1,1,27);
    }
}

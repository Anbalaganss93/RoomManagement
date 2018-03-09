package com.roommanagement.Utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by anbu0 on 04/03/2018.
 */

public class Util {
    public static int mCurrentyear;
    public static int mCurrentMonth;
    public static int mCurrentDay;

    public static void getCurrentDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        mCurrentyear = calendar.get(Calendar.YEAR);
        mCurrentMonth = calendar.get(Calendar.MONTH);
        mCurrentDay = calendar.get(Calendar.DAY_OF_MONTH);
    }
}

package com.itii.utils;

import java.util.Calendar;

public class DateFormatUtil
{

    public final static String DATE_FORMAT = "yyyy/MM/dd";
    private final static String SEPARATOR = "  ";

    public static String formatDate(String date, String time)
    {
        // "2018/12/20 20:20"
        return date + SEPARATOR + time;
    }

    /**
     * @param dateAndTime
     *            is the formatted date and time (aaaa-mm-dd hh:mm)
     * @return an array with first the Date, then the Time;
     */

    public static String[] unformatDate(String dateAndTime)
    {
        return dateAndTime.split(SEPARATOR);
    }

    public static String getDate()
    {
        final Calendar cal = Calendar.getInstance();

        final String year = String.format("%04d", cal.get(Calendar.YEAR));
        final String month = String.format("%02d", cal.get(Calendar.MONTH) + 1);
        final String day = String.format("%02d",
                cal.get(Calendar.DAY_OF_MONTH));

        return year + "/" + month + "/" + day;
    }
}

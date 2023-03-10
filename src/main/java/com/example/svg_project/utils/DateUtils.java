package com.example.svg_project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        return formatter.format(date);
    }
}

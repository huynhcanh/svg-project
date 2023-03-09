package com.example.svg_project.utils;

import java.util.List;

public class FormatUtils {
    public static String formatValue(String value){
        value = value.trim();
        StringBuilder s = new StringBuilder(value);
        for(int i = 0; i<s.length() - 1;i++){
            if(s.charAt(i) == ' ' && s.charAt(i+1) == ' '){
                s.deleteCharAt(i);
                i--;
            }
        }
        return s.toString();
    }

    public static String valueToCode(String value){
        StringBuilder s = new StringBuilder(value.toLowerCase());
        for(int i = 0; i<s.length();i++){
            if(s.charAt(i) == ' '){
                s.setCharAt(i, '-');
            }
        }
        return s.toString();
    }

    public static String arrayToString(List<?> arr){
        StringBuilder sb = new StringBuilder();
        sb.append("'");
        sb.append(arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            sb.append("', '");
            sb.append(arr.get(i));
        }
        sb.append("'");
        String result = sb.toString();
        return result;
    }
}

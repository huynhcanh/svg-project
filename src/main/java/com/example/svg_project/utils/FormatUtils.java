package com.example.svg_project.utils;

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
}

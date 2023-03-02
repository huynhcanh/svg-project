package com.example.svg_project.utils;

public class ExceptionUtils {
    public static String notFoundMessage(String s) {
        return s + " is not found";
    }

    public static String exsitValueMessage(String s) {
        return s + " is already exist";
    }

    public static String qtyNotEnoughMessage(Long id) {
        return "Quantity of item have id = " + id +" isn't enough";
    }
}

package com.luma.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CommonUtils {

    public static double getParsedPrice(String text) {
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            Number number = format.parse(text);
            return number.doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException("Can't parse the price: " + text, e);
        }
    }

}

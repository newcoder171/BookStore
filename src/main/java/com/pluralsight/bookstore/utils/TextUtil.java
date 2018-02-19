package com.pluralsight.bookstore.utils;

public class TextUtil {

    public String sanitize(String textToSanitize) {
        return textToSanitize.replaceAll("\\s+", " ");
    }
}

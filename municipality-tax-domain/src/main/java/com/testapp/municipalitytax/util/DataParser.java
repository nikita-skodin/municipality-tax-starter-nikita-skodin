package com.testapp.municipalitytax.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataParser {
    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return LocalDate.parse(date, formatter);
    }
}

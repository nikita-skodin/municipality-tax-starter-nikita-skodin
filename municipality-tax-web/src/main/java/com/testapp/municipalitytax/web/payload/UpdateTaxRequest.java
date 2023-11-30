package com.testapp.municipalitytax.web.payload;

public record UpdateTaxRequest(
        Double tax,

        String startDate,

        String schedule) {
}

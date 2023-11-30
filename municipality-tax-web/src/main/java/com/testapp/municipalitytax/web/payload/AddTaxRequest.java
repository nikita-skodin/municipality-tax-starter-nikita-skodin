package com.testapp.municipalitytax.web.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddTaxRequest(
        String municipality,
        Double tax,
        String startDate,
        String schedule) {
}

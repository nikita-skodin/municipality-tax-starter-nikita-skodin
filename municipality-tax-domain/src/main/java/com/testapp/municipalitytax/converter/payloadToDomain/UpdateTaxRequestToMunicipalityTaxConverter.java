package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UpdateTaxRequestToMunicipalityTaxConverter
        implements Converter<UpdateTaxRequest, MunicipalityTax> {

    @Override
    public MunicipalityTax convert(UpdateTaxRequest source) {
        return new MunicipalityTax(
                source.tax(),
                parseDate(source.startDate()),
                Schedule.valueOf(source.schedule().toUpperCase()));
    }

    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return LocalDate.parse(date, formatter);
    }

}

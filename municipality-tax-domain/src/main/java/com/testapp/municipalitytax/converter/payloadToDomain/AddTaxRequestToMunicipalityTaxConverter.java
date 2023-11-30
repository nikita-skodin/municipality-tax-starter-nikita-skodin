package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.util.DataParser;
import com.testapp.municipalitytax.web.payload.AddTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddTaxRequestToMunicipalityTaxConverter
        implements Converter<AddTaxRequest, MunicipalityTax> {

    @Override
    public MunicipalityTax convert(AddTaxRequest source) {
        return new MunicipalityTax(
                UUID.randomUUID(),
                source.municipality(),
                source.tax(),
                DataParser.parseDate(source.startDate()),
                Schedule.valueOf(source.schedule().toUpperCase()));
    }
}

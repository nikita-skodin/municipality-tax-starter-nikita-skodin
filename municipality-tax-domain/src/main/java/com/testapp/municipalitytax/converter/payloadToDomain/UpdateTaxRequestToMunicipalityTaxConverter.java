package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.util.DataParser;
import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateTaxRequestToMunicipalityTaxConverter
        implements Converter<UpdateTaxRequest, MunicipalityTax> {

    @Override
    public MunicipalityTax convert(UpdateTaxRequest source) {
        return new MunicipalityTax(
                source.tax(),
                DataParser.parseDate(source.startDate()),
                Schedule.valueOf(source.schedule().toUpperCase()));
    }
}

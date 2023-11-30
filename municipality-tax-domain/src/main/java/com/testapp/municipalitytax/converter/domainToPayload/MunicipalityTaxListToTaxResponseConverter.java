package com.testapp.municipalitytax.converter.domainToPayload;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.web.payload.TaxResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MunicipalityTaxListToTaxResponseConverter
        implements Converter<List<MunicipalityTax>, TaxResponse> {

    @Override
    public TaxResponse convert(List<MunicipalityTax> source) {
        return new TaxResponse(source.stream().map(MunicipalityTax::tax).toList());
    }
}

package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.web.payload.AddTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddTaxRequestToMunicipalityTaxConverter
    implements Converter<AddTaxRequest, MunicipalityTax> {

  @Override
  public MunicipalityTax convert(AddTaxRequest source) {
    throw new UnsupportedOperationException();
  }
}

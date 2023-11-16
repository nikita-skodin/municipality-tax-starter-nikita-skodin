package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UpdateTaxRequestToMunicipalityTaxConverter
    implements Converter<UpdateTaxRequest, MunicipalityTax> {

  @Override
  public MunicipalityTax convert(UpdateTaxRequest source) {
    throw new UnsupportedOperationException();
  }
}

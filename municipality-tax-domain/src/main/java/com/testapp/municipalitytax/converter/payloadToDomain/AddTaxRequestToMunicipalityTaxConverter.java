package com.testapp.municipalitytax.converter.payloadToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.web.payload.AddTaxRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            parseDate(source.startDate()),
            Schedule.valueOf(source.schedule().toUpperCase()));
  }

  public LocalDate parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    return LocalDate.parse(date, formatter);
  }

}

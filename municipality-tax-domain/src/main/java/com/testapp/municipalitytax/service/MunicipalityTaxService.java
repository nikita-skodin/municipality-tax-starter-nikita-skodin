package com.testapp.municipalitytax.service;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.TaxesRepository;
import com.testapp.municipalitytax.web.services.TaxesService;
import com.testapp.municipalitytax.web.payload.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class MunicipalityTaxService implements TaxesService {

  private final TaxesRepository taxesRepository;
  private final ConversionService conversionService;

  public MunicipalityTaxService(
      TaxesRepository taxesRepository, ConversionService conversionService) {
    this.taxesRepository = taxesRepository;
    this.conversionService = conversionService;
  }

  @Override
  public UUIDResponse addTax(AddTaxRequest addTaxRequest) {
    MunicipalityTax convert = conversionService.convert(addTaxRequest, MunicipalityTax.class);
    taxesRepository.save(convert);
    return new UUIDResponse(convert.id());
  }

  @Override
  public void updateTax(UUID taxId, UpdateTaxRequest updateTaxRequest) {
    MunicipalityTax convert = conversionService.convert(updateTaxRequest, MunicipalityTax.class);
    taxesRepository.update(convert.withId(taxId));

  }

  @Override
  public TaxResponse findTax(String municipality, String date) {
      return conversionService.convert(
            taxesRepository.findByMunicipalityAndDate(municipality, parseDate(date)),
            TaxResponse.class);
  }

  @Override
  public TaxListResponse getAllMunicipalityTaxes() {
    List<MunicipalityTax> allMunicipalityTaxes = taxesRepository.getAllMunicipalityTaxes();
    List<FullTaxInfo> list = allMunicipalityTaxes.stream()
            .map(o -> conversionService.convert(o, FullTaxInfo.class)).toList();
    return new TaxListResponse(list.size(), list);
  }


  public LocalDate parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    return LocalDate.parse(date, formatter);
  }
}

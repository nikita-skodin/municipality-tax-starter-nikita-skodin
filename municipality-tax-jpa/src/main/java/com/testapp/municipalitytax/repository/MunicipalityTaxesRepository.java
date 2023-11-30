package com.testapp.municipalitytax.repository;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.TaxesRepository;
import com.testapp.municipalitytax.entity.TaxEntity;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MunicipalityTaxesRepository implements TaxesRepository {

    private final TaxesJpaRepository taxesJpaRepository;
    private final ConversionService conversionService;

    public MunicipalityTaxesRepository(
            TaxesJpaRepository taxesJpaRepository, ConversionService conversionService) {
        this.taxesJpaRepository = taxesJpaRepository;
        this.conversionService = conversionService;
    }

    @Override
    public MunicipalityTax save(MunicipalityTax municipalityTax) {
        TaxEntity tax = conversionService.convert(municipalityTax, TaxEntity.class);
        taxesJpaRepository.save(tax);
        return municipalityTax;
    }

    @Override
    public int update(MunicipalityTax municipalityTax) {
        TaxEntity tax = conversionService.convert(municipalityTax, TaxEntity.class);
        taxesJpaRepository.save(tax);
        return 1;
    }

    @Override
    public List<MunicipalityTax> findByMunicipalityAndDate(String municipality, LocalDate date) {
        List<TaxEntity> byMunicipalityAndStartDate = taxesJpaRepository.findByMunicipalityAndStartDate(municipality, date);
        return byMunicipalityAndStartDate.stream().map(o -> conversionService.convert(o, MunicipalityTax.class)).toList();
    }

    @Override
    public List<MunicipalityTax> getAllMunicipalityTaxes() {
        return taxesJpaRepository.findAll().stream().map(o -> conversionService.convert(o, MunicipalityTax.class)).toList();
    }
}

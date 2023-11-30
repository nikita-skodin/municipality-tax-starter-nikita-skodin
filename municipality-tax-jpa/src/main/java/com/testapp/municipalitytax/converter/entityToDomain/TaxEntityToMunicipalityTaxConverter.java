package com.testapp.municipalitytax.converter.entityToDomain;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.Schedule;
import com.testapp.municipalitytax.entity.TaxEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.*;

@Component
public class TaxEntityToMunicipalityTaxConverter implements Converter<TaxEntity, MunicipalityTax> {
    @Override
    public MunicipalityTax convert(TaxEntity source) {
        return new MunicipalityTax(
                source.id(),
                source.municipality(),
                source.tax(),
                source.startDate(),
                dateToSchedule(source.startDate(), source.endDate()));
    }

    private Schedule dateToSchedule(LocalDate startDate, LocalDate endDate) {
        if (DAYS.between(startDate, endDate) <= 1) {
            return Schedule.DAILY;
        } else if (WEEKS.between(startDate, endDate) <= 1) {
            return Schedule.WEEKLY;
        } else if (MONTHS.between(startDate, endDate) <= 1) {
            return Schedule.MONTHLY;
        } else {
            return Schedule.YEARLY;
        }
    }
}

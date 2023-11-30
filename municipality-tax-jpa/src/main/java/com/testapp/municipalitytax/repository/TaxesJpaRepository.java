package com.testapp.municipalitytax.repository;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.entity.TaxEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesJpaRepository extends JpaRepository<TaxEntity, UUID> {
    List<TaxEntity> findByMunicipalityAndStartDate(String municipality, LocalDate date);
}

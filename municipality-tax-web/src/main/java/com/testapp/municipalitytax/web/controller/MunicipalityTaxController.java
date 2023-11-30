package com.testapp.municipalitytax.web.controller;

import com.testapp.municipalitytax.web.Dto.ErrorDTO;
import com.testapp.municipalitytax.web.payload.*;
import com.testapp.municipalitytax.web.services.TaxesService;
import com.testapp.municipalitytax.web.validators.AddTaxRequestValidator;
import com.testapp.municipalitytax.web.validators.UpdateTaxRequestValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/taxes")
@Validated
public class MunicipalityTaxController extends MainController {

    private final TaxesService taxesService;
    private final AddTaxRequestValidator addTaxRequestValidator;
    private final UpdateTaxRequestValidator updateTaxRequestValidator;

    public MunicipalityTaxController(
            TaxesService taxesService,
            AddTaxRequestValidator addTaxRequestValidator,
            UpdateTaxRequestValidator updateTaxRequestValidator
    ) {
        this.addTaxRequestValidator = addTaxRequestValidator;
        this.updateTaxRequestValidator = updateTaxRequestValidator;
        this.taxesService = taxesService;
    }

    /**
     * Adds new municipality tax record
     *
     * @param addTaxRequest body municipality is case-sensitive schedule is case-insensitive date is
     *                      accepted in format yyyy.mm.dd tax is between 0 and 1
     * @return UUID of created record
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    UUIDResponse addMunicipalityTax(@RequestBody AddTaxRequest addTaxRequest, BindingResult bindingResult) {
        addTaxRequestValidator.validate(addTaxRequest, bindingResult);
        checkBindingResult(bindingResult);
        return taxesService.addTax(addTaxRequest);
    }

    /**
     * Edit municipality tax values by id
     *
     * @param taxId            UUID
     * @param updateTaxRequest body schedule is case-insensitive date is accepted in format yyyy.mm.dd
     *                         tax is between 0 and 1
     * @return UUID of created record
     */
    @PutMapping(value = "/{taxId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    void updateMunicipalityTax(
            @PathVariable("taxId") UUID taxId, @RequestBody UpdateTaxRequest updateTaxRequest, BindingResult bindingResult) {
        updateTaxRequestValidator.validate(updateTaxRequest, bindingResult);
        checkBindingResult(bindingResult);
        taxesService.updateTax(taxId, updateTaxRequest);
    }

    /**
     * Find municipality tax record by municipality and date
     *
     * @param municipality case-sensitive
     * @param date         accepted in format yyyy.mm.dd
     * @return TaxResponse list of taxes applied with chosen municipality and date
     */
    @GetMapping(value = "/{municipality}/{date}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    TaxResponse findMunicipalityTax(
            @PathVariable("municipality") String municipality, @PathVariable("date") String date) {
        return taxesService.findTax(municipality, date);
    }

    /**
     * Find all municipality taxes
     *
     * @return TaxResponse list of all taxes in all municipalities
     */
    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    TaxListResponse getAllMunicipalityTaxes() {
        return taxesService.getAllMunicipalityTaxes();
    }
}

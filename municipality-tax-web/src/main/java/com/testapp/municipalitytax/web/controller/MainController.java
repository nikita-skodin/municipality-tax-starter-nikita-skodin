package com.testapp.municipalitytax.web.controller;

import com.testapp.municipalitytax.web.Dto.ErrorDTO;
import com.testapp.municipalitytax.web.exceptions.BadRequestException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class MainController {

    protected void checkBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (var error : allErrors) {
                if (Objects.equals(error.getCode(), "400")) {
                    throw new BadRequestException(error.getDefaultMessage());
                }
            }
        }
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorDTO> handleException(Exception e) {
        return ResponseEntity
                .status(400)
                .body(new ErrorDTO("BAD_REQUEST", e.getMessage()));

    }
}
package com.testapp.municipalitytax.web.validators;

import com.testapp.municipalitytax.web.payload.UpdateTaxRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UpdateTaxRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UpdateTaxRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        String pattern = "\\d{4}\\.\\d{2}\\.\\d{2}";
        Pattern datePattern = Pattern.compile(pattern);

        UpdateTaxRequest target1 = (UpdateTaxRequest) target;


        Matcher matcher = datePattern.matcher(target1.startDate());
        if (!matcher.matches()){
            errors.rejectValue("startDate", "400",
                    "startDate is invalid");
        }

    }


}

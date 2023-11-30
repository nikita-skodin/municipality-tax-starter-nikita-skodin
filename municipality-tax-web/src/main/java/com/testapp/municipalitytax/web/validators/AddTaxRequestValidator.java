package com.testapp.municipalitytax.web.validators;

import com.testapp.municipalitytax.web.payload.AddTaxRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddTaxRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AddTaxRequestValidator.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        String pattern = "\\d{4}\\.\\d{2}\\.\\d{2}";
        Pattern datePattern = Pattern.compile(pattern);


        AddTaxRequest target1 = (AddTaxRequest) target;

        if (target1.municipality().isBlank()){
            errors.rejectValue("municipality", "400",
                    "name can not be blank");
        }

        Matcher matcher = datePattern.matcher(target1.startDate());
        if (!matcher.matches()){
            errors.rejectValue("startDate", "400",
                    "startDate is invalid");
        }

    }


}

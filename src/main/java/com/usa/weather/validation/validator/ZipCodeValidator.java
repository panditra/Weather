package com.usa.weather.validation.validator;

import com.usa.weather.validation.constraint.ValidateZipCode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;
import java.util.regex.Pattern;

public class ZipCodeValidator implements ConstraintValidator<ValidateZipCode, String> {
    @Autowired
    Validator validator;

    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches("^[0-9]{5}(?:-[0-9]{4})?$",zipCode);
    }
}

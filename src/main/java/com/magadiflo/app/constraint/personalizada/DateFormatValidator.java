package com.magadiflo.app.constraint.personalizada;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    //Validamos si el String ingresado tiene un formato de fecha
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

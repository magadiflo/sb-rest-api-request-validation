package com.magadiflo.app.constraint.customizada;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringFieldValidator implements ConstraintValidator<StringField, String> {

    private Boolean notEmpty;
    private Integer min;
    private Integer max;
    private String messageNotEmpty;
    private String messageLength;

    @Override
    public void initialize(StringField field) {
        this.notEmpty = field.notEmpty();
        this.min = field.min();
        this.max = field.max();
        this.messageNotEmpty = field.messageNotEmpty();
        ;
        this.messageLength = field.messageLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (this.notEmpty && value.isEmpty()) {
            context.buildConstraintViolationWithTemplate(this.messageNotEmpty).addConstraintViolation();
            return false;
        }

        if((this.min > 0 || this.max < Integer.MAX_VALUE) && (value.length() < this.min || value.length() > this.max)) {
            context.buildConstraintViolationWithTemplate(this.messageLength).addConstraintViolation();
            return false;
        }
        return false;
    }

}

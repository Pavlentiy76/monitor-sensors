package com.example.monitorsensors.validator;

import com.example.monitorsensors.annotation.RangeCheck;
import com.example.monitorsensors.dto.RangeDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeCheckValidator implements ConstraintValidator<RangeCheck, RangeDto> {
    @Override
    public void initialize(RangeCheck constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RangeDto rangeDto, ConstraintValidatorContext constraintValidatorContext) {
        if (rangeDto.getFrom() != null && rangeDto.getTo() != null){
            return rangeDto.getFrom() < rangeDto.getTo();
        }
        return false;
    }
}
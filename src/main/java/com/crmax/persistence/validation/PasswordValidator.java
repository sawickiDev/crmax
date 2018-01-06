package com.crmax.persistence.validation;

import com.crmax.persistence.model.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordNotEmpty, Password> {
    @Override
    public void initialize(PasswordNotEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(Password password,
                           ConstraintValidatorContext constraintValidatorContext) {
        return password.getPassword() != null && !password.getPassword().trim().equals("");
    }
}

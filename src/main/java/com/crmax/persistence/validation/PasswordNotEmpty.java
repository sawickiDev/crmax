package com.crmax.persistence.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordNotEmpty {

    String message() default "Password Must Not Be Empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

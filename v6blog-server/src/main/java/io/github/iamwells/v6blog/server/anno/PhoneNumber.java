package io.github.iamwells.v6blog.server.anno;


import io.github.iamwells.v6blog.server.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "号码格式错误！";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};

    /**
     * 号码字段
     */
    String numberFieldName() default "mobilePhone";

    /**
     * 号码国家字段
     */
    String numberCountryFieldName() default "mobilePhoneCountry";
}

package io.github.iamwells.v6blog.server.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import io.github.iamwells.v6blog.server.anno.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;


public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, Object> {

    private String numberFieldName;
    private String numberCountryFieldName;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.numberFieldName = constraintAnnotation.numberFieldName();
        this.numberCountryFieldName = constraintAnnotation.numberCountryFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Class<?> aClass = value.getClass();
            Field numberField = aClass.getDeclaredField(numberFieldName);
            Field numberCountryField = aClass.getDeclaredField(numberCountryFieldName);
            numberCountryField.setAccessible(true);
            numberField.setAccessible(true);
            Object number = numberField.get(value);
            Object country = numberCountryField.get(value);
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            if (number != null) {
                if (country == null) {
                    country = "CN";
                }
                Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(number.toString(), country.toString());
                return phoneUtil.isValidNumber(phoneNumber);
            } else {
                return true;
            }
        } catch (NoSuchFieldException | IllegalAccessException | NumberParseException e) {
            throw new RuntimeException(e);
        }
    }
}

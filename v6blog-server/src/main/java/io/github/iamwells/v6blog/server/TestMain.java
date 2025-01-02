package io.github.iamwells.v6blog.server;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class TestMain {
    public static void main(String[] args) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber phoneNumber = phoneUtil.parse("0713-5293979", "CN");
            if (phoneUtil.isValidNumber(phoneNumber)) {
                System.out.println(phoneUtil.formatInOriginalFormat(phoneNumber, "CN"));
            }

        } catch (NumberParseException e) {
            throw new RuntimeException(e);
        }
    }
}

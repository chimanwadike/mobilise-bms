/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilise.bms.util;

import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.exception.GeneralAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Verifier {

    private final ExceptionThrower exceptionThrower;
    private String resourceUrl;

    @Autowired
    public Verifier(ExceptionThrower exceptionThrower) {
        this.exceptionThrower = exceptionThrower;
    }

    public Verifier setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public void verifyParams(String... params) throws GeneralAppException {
        for (String param : params) {
            if (param == null || param.isEmpty()) {
                exceptionThrower.throwNullParameterException(resourceUrl);
            }
        }
    }

    public void verifyObject(Object... objects) throws GeneralAppException {
        for (Object object : objects) {
            if (object == null) {
                exceptionThrower.throwNullParameterException(resourceUrl);
            }
        }
    }

    public boolean verifyEmail(String param) throws GeneralAppException {
        if (param == null || param.isEmpty()) {
            exceptionThrower.throwNullParameterException(resourceUrl);
        }

        var regexPattern = "/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/i";

        return patternMatches(param, regexPattern);
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}

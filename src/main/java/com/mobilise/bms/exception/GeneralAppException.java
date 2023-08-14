package com.mobilise.bms.exception;

import org.springframework.http.HttpStatus;

public class GeneralAppException extends RuntimeException{
    public GeneralAppException(HttpStatus httpStatus, String appErrorCode, String message, String resource) {
        super(GeneralAppException.generateErrorMessage(httpStatus.value(), appErrorCode, message, resource));
    }

    private static String generateErrorMessage(Integer httpStatusValue, String appErrorCode, String message, String resource) {
        return httpStatusValue.toString() + "|" + appErrorCode + "|" + message + "|" + resource;
    }
}

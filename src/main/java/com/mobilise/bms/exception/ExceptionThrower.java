package com.mobilise.bms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionThrower {
    //1000 Application-wide error codes
    private final String NULL_PARAMETER_APP_ERROR_CODE = "1000";
    private final String NULL_PARAMETER_ERROR = "Please provide all the required information";

    //2000 Book domain-related errors
    private final String BOOK_NOT_FOUND_APP_ERROR_CODE = "2000";
    private final String BOOK_NOT_FOUND_ERROR = "Requested book was not found";

    private final String INVALID_AUTHOR_IDS_APP_ERROR_CODE = "2001";
    private final String INVALID_AUTHOR_IDS_ERROR = "Invalid author ID(s) detected";

    private final String INVALID_GENRE_IDS_APP_ERROR_CODE = "2002";
    private final String INVALID_GENRE_IDS_ERROR = "Invalid genre ID(s) detected";

    private static final String INVALID_PUBLISH_YEAR_APP_ERROR_CODE = "2003";
    private static final String INVALID_PUBLISH_YEAR_ERROR = "Publish year must not be in the future";

    private final String AUTHOR_NOT_FOUND_APP_ERROR_CODE = "2004";
    private final String AUTHOR_NOT_FOUND_ERROR = "Requested author was not found";

    private final String GENRE_NOT_FOUND_APP_ERROR_CODE = "2005";
    private final String GENRE_NOT_FOUND_ERROR = "Requested genre was not found";

    private final String GENRE_ALREADY_EXISTS_APP_ERROR_CODE = "2006";
    private final String GENRE_ALREADY_EXISTS_ERROR = "Duplicate genre creation was prevented";


    public void throwNullParameterException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.BAD_REQUEST, NULL_PARAMETER_APP_ERROR_CODE, NULL_PARAMETER_ERROR, link);
    }

    public void throwBookNotFoundException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.NOT_FOUND, BOOK_NOT_FOUND_APP_ERROR_CODE, BOOK_NOT_FOUND_ERROR, link);
    }

    public void throwInvalidAuthorIdsDetectedException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.BAD_REQUEST, INVALID_AUTHOR_IDS_APP_ERROR_CODE, INVALID_AUTHOR_IDS_ERROR, link);
    }
    public void throwInvalidGenreIdsDetectedException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.BAD_REQUEST, INVALID_GENRE_IDS_APP_ERROR_CODE, INVALID_GENRE_IDS_ERROR, link);
    }

    public void throwInvalidPublishYearException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.BAD_REQUEST, INVALID_PUBLISH_YEAR_APP_ERROR_CODE, INVALID_PUBLISH_YEAR_ERROR, link);
    }

    public void throwAuthorNotFoundException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.NOT_FOUND, AUTHOR_NOT_FOUND_APP_ERROR_CODE, AUTHOR_NOT_FOUND_ERROR, link);
    }

    public void throwGenreNotFoundException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.NOT_FOUND, GENRE_NOT_FOUND_APP_ERROR_CODE, GENRE_NOT_FOUND_ERROR, link);
    }

    public void throwGenreAlreadyExistsException(String link) throws GeneralAppException {
        throw new GeneralAppException(HttpStatus.CONFLICT, GENRE_ALREADY_EXISTS_APP_ERROR_CODE, GENRE_ALREADY_EXISTS_ERROR, link);
    }
}

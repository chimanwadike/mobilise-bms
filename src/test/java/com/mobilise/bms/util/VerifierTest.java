package com.mobilise.bms.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.exception.GeneralAppException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;

public class VerifierTest {
    private Verifier verifier;

    @Mock
    private ExceptionThrower exceptionThrower;

    private final String API_LINK = "/api/v1/test";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        verifier = new Verifier(exceptionThrower);
        verifier.setResourceUrl(API_LINK);
    }

    @Test
    public void testVerifyParams_NullParameter_ThrowsException() {
        // Arrange
        String param1 = "value1";
        String param2 = "value2";

        //Mock
        doThrow(new GeneralAppException(HttpStatus.BAD_REQUEST, "1002", "Test", API_LINK))
                .when(exceptionThrower).throwNullParameterException(API_LINK);

        // Act and Assert
        assertThrows(GeneralAppException.class, () -> verifier.verifyParams(param1, null, param2));
    }

    @Test
    public void testVerifyParams_ValidParameters_NoExceptionThrown() {
        // Arrange
        String param1 = "value1";
        String param2 = "value2";
        String param3 = "value3";

        // Act and Assert
        assertDoesNotThrow(() -> verifier.verifyParams(param1, param2, param3));

        // Verify interactions with dependencies (optional)
        verifyNoInteractions(exceptionThrower);
    }


    @Test
    public void testVerifyObject_NullObject_ThrowsException() {
        // Arrange
        Object object1 = new Object();
        Object object2 = null;
        Object object3 = new Object();

        //Mock
        doThrow(new GeneralAppException(HttpStatus.BAD_REQUEST, "1002", "Test", API_LINK))
                .when(exceptionThrower).throwNullParameterException(API_LINK);

        // Act and Assert
        assertThrows(GeneralAppException.class, () -> verifier.verifyObject(object1, object2, object3));
    }

    @Test
    public void testVerifyObject_ValidObjects_NoExceptionThrown() {
        // Arrange
        Object object1 = new Object();
        Object object2 = new Object();
        Object object3 = new Object();

        // Act and Assert
        assertDoesNotThrow(() -> verifier.verifyObject(object1, object2, object3));

        // Verify interactions with dependencies (optional)
        verifyNoInteractions(exceptionThrower);
    }
}

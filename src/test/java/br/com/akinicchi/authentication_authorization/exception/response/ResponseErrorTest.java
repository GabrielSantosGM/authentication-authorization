package br.com.akinicchi.authentication_authorization.exception.response;

import br.com.akinicchi.authentication_authorization.exception.MessageErrorEnum;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class ResponseErrorTest {

    @InjectMocks
    private ResponseError responseError;

    @Test
    void testResponseErrorWithoutArgsConstructor(){
        responseError = new ResponseError();
        assertNotNull(responseError);
    }

    @Test
    void testResponseErrorWithArgsConstructor(){
        responseError = new ResponseError(MessageErrorEnum.THIRD_ROLE);
        assertNotNull(responseError.getMessageErrorEnum());
        assertNotNull(responseError.getTimestamp());
        assertNotNull(responseError.getCode());
        assertNotNull(responseError.getError());
        assertNotNull(responseError.getMessage());
    }

}
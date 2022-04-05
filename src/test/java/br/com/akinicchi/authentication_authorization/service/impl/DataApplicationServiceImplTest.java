package br.com.akinicchi.authentication_authorization.service.impl;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import br.com.akinicchi.authentication_authorization.exception.HeaderParamsAuthenticationException;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseBody;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseError;
import br.com.akinicchi.authentication_authorization.mocks.DataApplicationMock;
import br.com.akinicchi.authentication_authorization.mocks.ResponseBodyMock;
import br.com.akinicchi.authentication_authorization.repository.DataApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

class DataApplicationServiceImplTest {

    @InjectMocks
    private DataApplicationServiceImpl dataApplicationService;

    @Mock
    private DataApplicationRepository dataApplicationRepository;

    @Mock
    private ResponseError responseError;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDataApplication() {
        when(dataApplicationRepository.findById(anyString())).thenReturn(Mono.just(DataApplicationMock.createDataApplication()));
        ResponseBody<DataApplication> dataApplicationResponseBody = ResponseBodyMock.createResponseBodyWithDataApplication();

        DataApplication dataApplication = dataApplicationService.findByIdApplication(anyString(), DataApplicationMock.SECRET_ID);

        assertNotNull(dataApplicationResponseBody.getContent());
        assertNotNull(dataApplication);
        assertEquals(DataApplicationMock.APPLICATION_NAME, dataApplication.getApplicationName());
        assertEquals(DataApplicationMock.SECRET_ID, dataApplication.getSecretId());
        assertEquals(DataApplicationMock.LAST_UPDATED, dataApplication.getLastUpdated());
        assertEquals(DataApplicationMock.REGISTRATION_STATUS, dataApplication.isRegistrationStatus());
    }

    @Test
    void getDataApplicationWhenReturnNotFound() {
        when(dataApplicationRepository.findById(anyString())).thenReturn(Mono.empty());
        ResponseBody<DataApplication> responseBodyWithError = ResponseBodyMock
                .createResponseBodyWithoutDataApplication(responseError);

        try {
            dataApplicationService.findByIdApplication(anyString(), DataApplicationMock.SECRET_ID);
        } catch (HeaderParamsAuthenticationException ex) {
            assertNotNull(responseBodyWithError.getResponseError());
            assertNotNull(ex);
            assertEquals(HttpStatus.NOT_FOUND, ex.getMessageErrorEnum().getHttpStatus());
            assertEquals(HeaderParamsAuthenticationException.class, ex.getClass());
        }
    }

    @Test
    void getDataApplicationWhenReturnSecretInvalid() {
        when(dataApplicationRepository.findById(anyString())).thenReturn(Mono.just(DataApplicationMock.createDataApplication()));
        ResponseBody<DataApplication> responseBodyWithError = ResponseBodyMock
                .createResponseBodyWithoutDataApplication(responseError);

        try {
            dataApplicationService.findByIdApplication(anyString(), DataApplicationMock.SECRET_ID_INVALID);
        } catch (HeaderParamsAuthenticationException ex) {
            assertNotNull(responseBodyWithError.getResponseError());
            assertNotNull(ex);
            assertEquals(HttpStatus.BAD_REQUEST, ex.getMessageErrorEnum().getHttpStatus());
            assertEquals(HeaderParamsAuthenticationException.class, ex.getClass());
        }
    }
}
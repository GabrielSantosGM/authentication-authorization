package br.com.akinicchi.authentication_authorization.security;

import br.com.akinicchi.authentication_authorization.exception.HeaderParamsAuthenticationException;
import br.com.akinicchi.authentication_authorization.mocks.RequestFilterMocks;
import br.com.akinicchi.authentication_authorization.service.DataApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RequestFilterTest {

    @InjectMocks
    private RequestFilter requestFilter;

    @Mock
    private DataApplicationService dataApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "http://127.0.0.1:8080/health-check, 200",
            "http://127.0.0.1:8080/random-url, 200"
    })
    void testRequestWithoutAuthentication(String url, int code) throws ServletException, IOException {
        MockHttpServletRequest request = RequestFilterMocks.createMockHttpRequest(url);
        MockHttpServletResponse response = RequestFilterMocks.createMockResponse(code);
        requestFilter.doFilterInternal(request, response, RequestFilterMocks.createMockChain());

        assertNotNull(request);
        assertNotNull(response);
        assertEquals(url, request.getServletPath());
        assertEquals(RequestFilterMocks.STATUS_OK, response.getStatus());
    }

    @Test
    void getPublicKeySuccess() throws ServletException, IOException {
        MockHttpServletRequest request = RequestFilterMocks.createMockHttpRequestPublicKey(
                RequestFilterMocks.URL_PUBLIC_KEY,
                RequestFilterMocks.CLIENT_ID_VALUE,
                RequestFilterMocks.SECRET_ID_VALUE
        );
        MockHttpServletResponse response = RequestFilterMocks.createMockResponse(RequestFilterMocks.STATUS_OK);

        requestFilter.doFilterInternal(request, response, RequestFilterMocks.createMockChain());

        assertEquals(RequestFilterMocks.URL_PUBLIC_KEY, request.getServletPath());
        assertEquals(RequestFilterMocks.CLIENT_ID_VALUE, request.getHeader(RequestFilterMocks.CLIENT_ID_HEADER));
        assertEquals(RequestFilterMocks.SECRET_ID_VALUE, request.getHeader(RequestFilterMocks.SECRET_ID_HEADER));
        assertEquals(RequestFilterMocks.STATUS_OK, response.getStatus());
    }

    @Test
    void testRequestWithoutHeadersParams() throws ServletException, IOException {
        MockHttpServletRequest request = RequestFilterMocks.createMockHttpRequest(RequestFilterMocks.URL_PUBLIC_KEY);
        MockHttpServletResponse response = RequestFilterMocks.createMockResponse(RequestFilterMocks.STATUS_BAD_REQUEST);

        String clientId = request.getHeader(RequestFilterMocks.CLIENT_ID_HEADER);
        String secretId = request.getHeader(RequestFilterMocks.SECRET_ID_HEADER);

        requestFilter.doFilterInternal(request, response, RequestFilterMocks.createMockChain());

        assertNull(clientId);
        assertNull(secretId);
        assertEquals(RequestFilterMocks.STATUS_BAD_REQUEST, response.getStatus());
    }

    @Test
    void testRequestWithInvalidParams() {
        MockHttpServletRequest request = RequestFilterMocks.createMockHttpRequestPublicKey(
                RequestFilterMocks.URL_PUBLIC_KEY,
                RequestFilterMocks.CLIENT_ID_VALUE,
                RequestFilterMocks.SECRET_ID_VALUE
        );
        MockHttpServletResponse response = RequestFilterMocks.createMockResponse(RequestFilterMocks.STATUS_BAD_REQUEST);

        String clientId = request.getHeader(RequestFilterMocks.CLIENT_ID_HEADER);

        when(dataApplicationService.findByIdApplication(anyString(), anyString()))
                .thenThrow(HeaderParamsAuthenticationException.class);

        try {
            requestFilter.doFilterInternal(request, response, RequestFilterMocks.createMockChain());
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals(RequestFilterMocks.URL_PUBLIC_KEY, request.getServletPath());
            assertEquals(RequestFilterMocks.CLIENT_ID_VALUE, clientId);
            assertEquals(RequestFilterMocks.STATUS_BAD_REQUEST, response.getStatus());
        }
    }
}
package br.com.akinicchi.authentication_authorization.mocks;

import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class RequestFilterMocks {

    public static final String URL_PUBLIC_KEY = "/public-key";
    public static final String CLIENT_ID_HEADER = "clientId";
    public static final String CLIENT_ID_VALUE = "clientIdValue";
    public static final String SECRET_ID_HEADER = "secretId";
    public static final String SECRET_ID_VALUE = "secretIdValue";
    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;

    public static MockHttpServletRequest createMockHttpRequest(String url) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath(url);
        return request;
    }

    public static MockHttpServletRequest createMockHttpRequestPublicKey(String url, String clientId, String secretId) {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setServletPath(url);
        request.addHeader(CLIENT_ID_HEADER, clientId);
        request.addHeader(SECRET_ID_HEADER, secretId);
        return request;
    }

    public static MockHttpServletResponse createMockResponse(int code) {
        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(code);
        return response;
    }

    public static MockFilterChain createMockChain() {
        return new MockFilterChain();
    }

}

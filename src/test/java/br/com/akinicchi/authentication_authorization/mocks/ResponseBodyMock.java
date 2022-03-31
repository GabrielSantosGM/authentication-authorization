package br.com.akinicchi.authentication_authorization.mocks;

import br.com.akinicchi.authentication_authorization.entity.DataApplication;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseBody;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseError;

public class ResponseBodyMock {

    public static ResponseBody<DataApplication> createResponseBodyWithDataApplication(){
        ResponseBody<DataApplication> responseBody = new ResponseBody<>();
        responseBody.setContent(DataApplicationMock.createDataApplication());
        return responseBody;
    }

    public static ResponseBody<DataApplication> createResponseBodyWithoutDataApplication(ResponseError responseError){
        ResponseBody<DataApplication> responseBody = new ResponseBody<>();
        responseBody.setResponseError(responseError);
        return responseBody;
    }
}

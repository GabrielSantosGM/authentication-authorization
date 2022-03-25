package br.com.akinicchi.authentication_authorization.exception.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBody<T> {

    private T content;
    private ResponseError responseError;
}

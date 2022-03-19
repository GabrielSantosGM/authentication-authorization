package br.com.akinicchi.authentication_authorization.exception;

import br.com.akinicchi.authentication_authorization.utils.ConstantUtil;

public class SecretIdIncorrectException extends RuntimeException {

    public SecretIdIncorrectException() {
        super(ConstantUtil.SECRET_ID_INVALID);
    }
}

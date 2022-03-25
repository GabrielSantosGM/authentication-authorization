package br.com.akinicchi.authentication_authorization.exception;

import lombok.Getter;

@Getter
public class HeaderParamsAuthenticationException extends RuntimeException {

    private final MessageErrorEnum messageErrorEnum;

    public HeaderParamsAuthenticationException(MessageErrorEnum messageErrorEnum) {
        super(messageErrorEnum.getTextMessage());
        this.messageErrorEnum = messageErrorEnum;
    }
}

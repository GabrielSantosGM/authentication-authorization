package br.com.akinicchi.authentication_authorization.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MessageErrorEnum {

    FIRST_ROLE("ClientId não cadastrado.", HttpStatus.NOT_FOUND),
    SECOND_ROLE("SecretId não autorizado.", HttpStatus.BAD_REQUEST),
    THIRD_ROLE("Campos ClientId e SecretId não podem ser nulos.", HttpStatus.BAD_REQUEST);

    private final String textMessage;
    private final HttpStatus httpStatus;

    MessageErrorEnum(String textMessage, HttpStatus httpStatus) {
        this.textMessage = textMessage;
        this.httpStatus = httpStatus;
    }
}

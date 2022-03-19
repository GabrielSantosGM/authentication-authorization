package br.com.akinicchi.authentication_authorization.exception.advice;

import br.com.akinicchi.authentication_authorization.exception.SecretIdIncorrectException;
import br.com.akinicchi.authentication_authorization.exception.response.ResponseError;
import br.com.akinicchi.authentication_authorization.utils.ConstantUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorAdviceHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseError listenUsernameNotFoundException(HttpServletResponse response){
        return ResponseError
                .Builder
                .create()
                .withTimestamp(LocalDateTime.now())
                .withCode(response.getStatus())
                .withError(HttpStatus.NO_CONTENT.toString())
                .withMessage(ConstantUtil.CLIENT_NOT_REGISTERED)
                .build();
    }

    @ExceptionHandler(SecretIdIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError listenSecretIdIncorrectException(HttpServletResponse response){
        return ResponseError
                .Builder
                .create()
                .withTimestamp(LocalDateTime.now())
                .withCode(response.getStatus())
                .withError(HttpStatus.BAD_REQUEST.toString())
                .withMessage(ConstantUtil.SECRET_ID_INVALID)
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError listenIllegalArgumentException(HttpServletResponse response){
        return ResponseError
                .Builder
                .create()
                .withTimestamp(LocalDateTime.now())
                .withCode(response.getStatus())
                .withError(HttpStatus.BAD_REQUEST.toString())
                .withMessage(ConstantUtil.INVALID_ARGUMENTS)
                .build();
    }

}

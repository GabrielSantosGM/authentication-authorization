package br.com.akinicchi.authentication_authorization.exception.response;

import br.com.akinicchi.authentication_authorization.exception.MessageErrorEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@JsonIgnoreProperties(value = {"messageErrorEnum", "sdf"})
public class ResponseError {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private MessageErrorEnum messageErrorEnum;

    private String timestamp;
    private String code;
    private String error;
    private String message;

    public ResponseError() {
    }

    public ResponseError(MessageErrorEnum messageErrorEnum, String... extraInfo) {
        this.messageErrorEnum = messageErrorEnum;
        this.timestamp = sdf.format(new Date());
        this.code = messageErrorEnum.getHttpStatus().getReasonPhrase();
        this.error = messageErrorEnum.name();
        this.message = (extraInfo == null) ? messageErrorEnum.getTextMessage() : String.format(messageErrorEnum.getTextMessage(), extraInfo);
    }
}

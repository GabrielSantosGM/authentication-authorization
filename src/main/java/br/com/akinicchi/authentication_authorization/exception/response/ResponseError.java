package br.com.akinicchi.authentication_authorization.exception.response;

import java.time.LocalDateTime;

public class ResponseError {

    private LocalDateTime timestamp;
    private int code;
    private String error;
    private String message;

    public ResponseError() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static final class Builder {
        private LocalDateTime timestamp;
        private int code;
        private String error;
        private String message;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withCode(int code) {
            this.code = code;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseError build() {
            ResponseError responseError = new ResponseError();
            responseError.setTimestamp(timestamp);
            responseError.setCode(code);
            responseError.setError(error);
            responseError.setMessage(message);
            return responseError;
        }
    }
}

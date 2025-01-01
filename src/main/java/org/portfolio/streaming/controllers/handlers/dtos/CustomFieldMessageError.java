package org.portfolio.streaming.controllers.handlers.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomFieldMessageError {
    private List<FieldErrorsDTO> fieldErrorsDTO;
    private Instant timestamp;
    private Integer status;
    private String path;


    public CustomFieldMessageError(Instant timestamp, Integer status, String path) {

        this.fieldErrorsDTO = new ArrayList<>();
        this.timestamp = timestamp;
        this.status = status;
        this.path = path;
    }

    public void addFieldError(String field, String message) {

        fieldErrorsDTO.add(new FieldErrorsDTO(field, message));

    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public List<FieldErrorsDTO> getFieldErrors () {


        return fieldErrorsDTO;

    }

    private static class FieldErrorsDTO {


        private String field, message;


        public FieldErrorsDTO(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }

}

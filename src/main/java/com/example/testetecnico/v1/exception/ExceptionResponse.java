package com.example.testetecnico.v1.exception;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    private String errorCode;
    private String errorMessage;
    private List<String> errors;

}

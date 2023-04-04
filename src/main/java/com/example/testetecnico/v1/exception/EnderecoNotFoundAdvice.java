package com.example.testetecnico.v1.exception;

import com.example.testetecnico.v1.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EnderecoNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PessoaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageDTO enderecoNotFoundHandler(EnderecoNotFoundException e) {

        return new MessageDTO(e.toString());
    }
}

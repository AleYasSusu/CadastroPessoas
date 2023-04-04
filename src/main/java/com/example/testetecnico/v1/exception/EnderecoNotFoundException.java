package com.example.testetecnico.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnderecoNotFoundException extends RuntimeException {

    private Long id;

    @Override
    public String toString() {
        return "O Endereço com id="+ id + " não foi encontrado";
    }
}


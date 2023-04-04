package com.example.testetecnico.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PessoaNotFoundException extends RuntimeException {

    private Long id;

    @Override
    public String toString() {
        return "A pessoa com id="+ id + " não foi encontrada";
    }
}

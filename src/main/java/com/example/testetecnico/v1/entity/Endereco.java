package com.example.testetecnico.v1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String cidade;

    @Enumerated(EnumType.STRING)
    private Principal principal;
    @Id
    private Long id;

    public Endereco(RecordEndereco dado) {
        this.logradouro = dado.logradouro();
        this.numero = dado.numero();
        this.cidade = dado.cidade();
        this.principal = dado.principal();
    }

}

package com.example.testetecnico.v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "pessoas")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dataNascimento;

    @Embedded
    private List<Endereco> endereco;

    public Pessoa(DadosCadastroPessoa dado) {
        this.nome = dado.nome();
        this.dataNascimento = dado.dataNascimento();
        List<Endereco> list = new ArrayList();
        for(int x =0; x < dado.endereco().size(); x++) {
            Endereco end = new Endereco(dado.endereco().get(x));
            list.add(end);
        }

        this.endereco = list;
    }
}

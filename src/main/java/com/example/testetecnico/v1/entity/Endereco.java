package com.example.testetecnico.v1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco", schema = "public")
public class Endereco implements Serializable {
    private String logradouro;

    private String cep;

    private String numero;

    private String cidade;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}

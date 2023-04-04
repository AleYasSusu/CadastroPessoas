package com.example.testetecnico.v1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoa", schema = "public")
public class Pessoa {
    private String name;
    private String dataNasc;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonManagedReference
    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos = new ArrayList<>();


}

package com.example.testetecnico.v1.dto;

import com.example.testetecnico.v1.entity.Endereco;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Long id;

    @NotNull(message = "Um nome deve ser informado")
    private String nome;

    @NotNull(message = "Um endereço deve ser informado")
    private List<Endereco> enderecos = new ArrayList<>();

    @NotNull(message = "Uma data de Nascimento deve ser informado")
    private String dataNasc;
}

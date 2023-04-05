package com.example.testetecnico.v1.entity;
import java.util.List;
public record DadosCadastroPessoa(String nome, String dataNascimento, List<RecordEndereco> endereco) {


}

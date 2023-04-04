package com.example.testetecnico.v1.controller;

import com.example.testetecnico.v1.dto.EnderecoDTO;
import com.example.testetecnico.v1.entity.Endereco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface EnderecoController {
    @GetMapping("v1/endereco")
    List<Endereco> getEndereco();

    @GetMapping("v1/enderecos/{id}")
    ResponseEntity<Endereco> one(Long id);

    @PostMapping("v1/enderecos")
    Endereco novoEndereco(EnderecoDTO pessoa);
}

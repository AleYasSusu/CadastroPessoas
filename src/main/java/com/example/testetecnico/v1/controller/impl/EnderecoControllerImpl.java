package com.example.testetecnico.v1.controller.impl;

import com.example.testetecnico.v1.controller.EnderecoController;
import com.example.testetecnico.v1.dto.EnderecoDTO;
import com.example.testetecnico.v1.entity.Endereco;
import com.example.testetecnico.v1.exception.EnderecoNotFoundException;

import com.example.testetecnico.v1.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class EnderecoControllerImpl implements EnderecoController {
    private final EnderecoService enderecoService;

    @Override
    @GetMapping("v1/endereco")
    public List<Endereco> getEndereco() {
        return enderecoService.get();
    }

    @Override
    @GetMapping("v1/enderecos/{id}")
    public ResponseEntity<Endereco> one(Long id) {
        return enderecoService.get(id)
                .map(endereco -> ResponseEntity.ok().body(endereco))
                .orElseThrow(() -> new EnderecoNotFoundException(id));
    }

    @Override
    @PostMapping("v1/enderecos")
    public Endereco novoEndereco(EnderecoDTO endereco) {
        return enderecoService.save(new ModelMapper().map(endereco, Endereco.class));
    }
}

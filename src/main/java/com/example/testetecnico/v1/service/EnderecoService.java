package com.example.testetecnico.v1.service;

import com.example.testetecnico.v1.entity.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoService {
    List<Endereco> get();

    Optional<Endereco> get(Long id);

    Endereco save(Endereco endereco);
}

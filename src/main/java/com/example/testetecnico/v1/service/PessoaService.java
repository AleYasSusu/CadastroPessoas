package com.example.testetecnico.v1.service;


import com.example.testetecnico.v1.entity.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    List<Pessoa> get();

    Optional<Pessoa> get(Long id);

    Pessoa save(Pessoa pessoa);

    Optional<Pessoa> update(Pessoa novaPessoa, Long id);

    void deleteByID(Long id);
}

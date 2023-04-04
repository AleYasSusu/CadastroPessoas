package com.example.testetecnico.v1.service.impl;

import com.example.testetecnico.v1.entity.Endereco;
import com.example.testetecnico.v1.repository.EnderecoRepository;
import com.example.testetecnico.v1.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {
   private final EnderecoRepository enderecoRepository;
    @Override
    public List<Endereco> get() {
        return enderecoRepository.findAll();
    }

    @Override
    public Optional<Endereco> get(Long id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
}

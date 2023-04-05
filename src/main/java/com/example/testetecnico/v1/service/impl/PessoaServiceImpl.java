package com.example.testetecnico.v1.service.impl;

import com.example.testetecnico.v1.entity.Pessoa;
import com.example.testetecnico.v1.exception.PessoaNotFoundException;
import com.example.testetecnico.v1.repository.PessoaRepository;
import com.example.testetecnico.v1.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {
    private final PessoaRepository pessoarepository;

    @Override
    public List<Pessoa> get() {
        return pessoarepository.findAll();
    }

    @Override
    public Optional<Pessoa> get(Long id) {
        return pessoarepository.findById(id);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoarepository.save(pessoa);
    }

    @Override
    public Optional<Pessoa> update(Pessoa dado, Long id) {
        return pessoarepository.findById(id).
                map(pessoa -> {
                    setIfNotNull(dado::setNome, dado.getNome());
                    setIfNotNull(dado::setEndereco, dado.getEndereco());
                    setIfNotNull(dado::setDataNascimento, dado.getDataNascimento());

                    return pessoarepository.save(pessoa);
                });
    }

    @Override
    public void deleteByID(Long id) {
        Optional<Pessoa> pessoa = get(id);

        if(!pessoa.isPresent()) {
            pessoarepository.deleteById(id);
        } else {
            throw new PessoaNotFoundException(id);
        }
    }

    private <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}

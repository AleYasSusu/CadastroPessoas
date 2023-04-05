package com.example.testetecnico.v1.controller.impl;

import com.example.testetecnico.v1.controller.PessoaController;
import com.example.testetecnico.v1.entity.DadosCadastroPessoa;
import com.example.testetecnico.v1.entity.Pessoa;
import com.example.testetecnico.v1.exception.PessoaNotFoundException;
import com.example.testetecnico.v1.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "pessoas/")
@RequiredArgsConstructor
public class PessoaControllerImpl implements PessoaController {
    private final PessoaService pessoaService;

    @Override
    @GetMapping("v1/pessoas")
    public List<Pessoa> getPessoa() {
        return pessoaService.get();
    }

    @Override
    @GetMapping("v1/pessoas/{id}")
    public ResponseEntity<Pessoa> one(Long id) {
        return pessoaService.get(id)
                .map(pessoa -> ResponseEntity.ok().body(pessoa))
                .orElseThrow(() -> new PessoaNotFoundException(id));
    }

    @Override
    @PostMapping("v1/pessoas")
    public void cadastrar(@RequestBody DadosCadastroPessoa dado) {
        pessoaService.save(new Pessoa(dado));
    }


    @Override
    @PutMapping("v1/pessoas/{id}")
    public Pessoa updatePessoa(DadosCadastroPessoa dado, Long id) {
        return pessoaService.update(new ModelMapper()
                        .map(dado, Pessoa.class), id)
                .orElseThrow(() -> {
                    throw new PessoaNotFoundException(id);
                });
    }

    @Override
    @DeleteMapping("v1/pessoas/{id}")
    public void delete(Long id) {
        pessoaService.deleteByID(id);
    }
}

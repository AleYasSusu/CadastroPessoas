package com.example.testetecnico.v1.controller;

import com.example.testetecnico.v1.entity.DadosCadastroPessoa;
import com.example.testetecnico.v1.entity.Pessoa;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

public interface PessoaController  {

    @ApiOperation(value = "Lista todas as pessoas cadastradas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pessoas encontradas")
    })
    @GetMapping("/api/v1/pessoas")
    List<Pessoa> getPessoa();

    @ApiOperation(value = "Retorna uma pessoa pelo seu id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pessoa encontrada", response = Pessoa.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    ResponseEntity<Pessoa> one(@PathVariable Long id);

    @ApiOperation(value = "Adiciona uma pessoa")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pessoa criada com sucesso", response = Pessoa.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    void cadastrar(@Valid @RequestBody DadosCadastroPessoa dado);
    @ApiOperation(value = "Altera uma pessoa")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pessoa criada com sucesso", response = Pessoa.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    Pessoa updatePessoa(DadosCadastroPessoa dado, Long id);

    @ApiOperation(value = "Remove uma pessoa pelo seu id")
    @ApiResponses( {
            @ApiResponse(code = 204, message = "Pessoa removida com sucesso"),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    void delete(@PathVariable Long id);
}

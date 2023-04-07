package com.aleDev.TesteTecnico.v1.controller;

import com.aleDev.TesteTecnico.v1.entity.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface PersonController {
    @ApiOperation(value = "Lista todas as pessoas cadastradas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pessoas encontradas")
    })
    List<Person> findAll();

    @ApiOperation(value = "Retorna uma pessoa pelo seu id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pessoa encontrada", response = Person.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    Person findById(@PathVariable Integer id);



    @ApiOperation(value = "Adiciona uma pessoa")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pessoa criada com sucesso", response = Person.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    ResponseEntity<Person> saveNewPerson(Person pessoa);



    @ApiOperation(value = "Altera uma pessoa")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No content", response = Person.class),
            @ApiResponse(code = 404, message = "Pessoa não encontrada")
    })
    ResponseEntity<Void> updatePerson(@PathVariable Integer id, @RequestBody Person person);
}

package com.aleDev.TesteTecnico.v1.controller;


import com.aleDev.TesteTecnico.v1.entity.Adress;
import com.aleDev.TesteTecnico.v1.entity.Person;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AdressController {


    @ApiOperation(value = "Retorna um endereço pelo seu id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Endereço encontrada", response = Person.class),
            @ApiResponse(code = 404, message = "Endereço não encontrado")
    })
    Adress findById(@PathVariable Integer id);

    @ApiOperation(value = "Lista todos os endereços cadastrados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "endereços encontrados")
    })
    List<Adress> findAll();

    @ApiOperation(value = "Adiciona um endereço")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Endereço criado com sucesso", response = Adress.class),
            @ApiResponse(code = 404, message = "Endereço não encontrada")
    })
    ResponseEntity<Adress> saveNewAdress(@RequestBody Adress adress);


    List<Adress> searchAdresssByPerson(@PathVariable Integer id);


    Adress setPrincipalByIdPerson(@PathVariable Integer id);
}

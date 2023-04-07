package com.aleDev.TesteTecnico.v1.service;


import com.aleDev.TesteTecnico.v1.entity.Adress;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AdressService {

    List<Adress> findAll();

    Adress saveNewAdress(Adress Adress);

    Adress setPrincipalByIdPerson(Integer id);

    List<Adress> searchAdresssByPerson(Integer id);

    Adress findById(Integer id);
}

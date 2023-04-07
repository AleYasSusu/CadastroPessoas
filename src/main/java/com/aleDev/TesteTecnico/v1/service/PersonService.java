package com.aleDev.TesteTecnico.v1.service;


import com.aleDev.TesteTecnico.v1.entity.Person;

import java.util.List;

public interface PersonService {


    Person findById(Integer id);

    Person saveNewPerson(Person pessoa);

    List<Person> findAll();

    Person updatePerson(Integer id, Person updatePerson);
}

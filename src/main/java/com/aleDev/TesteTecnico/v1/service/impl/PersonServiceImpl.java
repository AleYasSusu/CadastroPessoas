package com.aleDev.TesteTecnico.v1.service.impl;

import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.exception.EntitiesNotFoundException;
import com.aleDev.TesteTecnico.v1.repository.PersonRepository;
import com.aleDev.TesteTecnico.v1.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person findById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntitiesNotFoundException(
                        String.format("Pessoa com o id %d não encontrada", id)));
    }

    @Override
    public Person saveNewPerson(Person pessoa) {
        return personRepository.save(pessoa);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person updatePerson(Integer id, Person updatePerson) {
        Person personExisting = personRepository.findById(id)
                .orElseThrow(() -> new EntitiesNotFoundException("Pessoa não encontrada"));
        personExisting.setName(updatePerson.getName());
        personExisting.setBirthDate(updatePerson.getBirthDate());
        return personRepository.save(updatePerson);
    }
}

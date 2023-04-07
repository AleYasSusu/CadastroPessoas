package com.aleDev.TesteTecnico.service;


import com.aleDev.TesteTecnico.builder.PersonBuilder;
import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.repository.PersonRepository;
import com.aleDev.TesteTecnico.v1.service.impl.PersonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static java.util.Optional.of;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepository personRepository;


    @Test
    public void shouldFindallPerson() {
        //Data
        List<Person> allPerson = PersonBuilder.buildPersonDefault().buildList();
        when(personRepository.findAll()).thenReturn(allPerson);
        //Action
        List<Person> response = personService.findAll();

        //Result
        Assertions.assertThat(response).isEqualTo(allPerson);
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindPersonById() {
        //Data
        Person session = PersonBuilder.buildPersonDefault().build();
        when(personRepository.findById(1)).thenReturn(of(session));

        //Action
        Person response = personService.findById(1);

        //Result
        Assertions.assertThat(response).isEqualTo(session);
        verify(personRepository, times(1)).findById(1);
    }

    @Test
    public void shouldSaveNewPerson() {
        //Data
        Person person = PersonBuilder.buildPersonDefault().build();
        when(personRepository.save(person)).thenReturn(person);

        //Action
        personService.saveNewPerson(person);

        //Result
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void shouldUpdatePerson() {
        //Data
        Person person = PersonBuilder.buildPersonDefault().build();
        when(personRepository.findById(1)).thenReturn(Optional.ofNullable(person));

        //Action
        personService.updatePerson(1, person);

        //Result
        verify(personRepository, times(1)).save(person);
    }
}

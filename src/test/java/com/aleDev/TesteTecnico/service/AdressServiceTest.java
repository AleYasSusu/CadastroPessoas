package com.aleDev.TesteTecnico.service;

import com.aleDev.TesteTecnico.builder.AdressBuilder;
import com.aleDev.TesteTecnico.builder.PersonBuilder;
import com.aleDev.TesteTecnico.v1.entity.Adress;
import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.repository.AdressRepository;
import com.aleDev.TesteTecnico.v1.service.PersonService;
import com.aleDev.TesteTecnico.v1.service.impl.AdressServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdressServiceTest {

    @InjectMocks
    private AdressServiceImpl adressService;
    @Mock
    private AdressRepository adressRepository;
    @Mock
    private PersonService personService;

    @Test
    public void shouldFindAllAdress() {
        //Data
        List<Adress> allAdress = AdressBuilder.buildAdressDefault().buildList();
        when(adressRepository.findAll()).thenReturn(allAdress);
        //Action
        List<Adress> response = adressService.findAll();

        //Result
        Assertions.assertThat(response).isEqualTo(allAdress);
        verify(adressRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindAdressById() {
        //Data
        Adress adress = AdressBuilder.buildAdressDefault().build();
        when(adressRepository.findById(1L)).thenReturn(of(adress));

        //Action
        Adress response = adressService.findById(1);

        //Result
        Assertions.assertThat(response).isEqualTo(adress);
        verify(adressRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldSaveNewAdress() {
        //Data
        Adress adress = AdressBuilder.buildAdressDefault().build();
        when(adressRepository.save(adress)).thenReturn(adress);

        //Action
        adressService.saveNewAdress(adress);

        //Result
        verify(adressRepository, times(1)).save(adress);
    }

    @Test
    public void shouldSetPrincipalByIdPerson() {
        //Data
        List<Adress> AllAdress = AdressBuilder.buildAdressDefault().buildList();
        Adress adress = AdressBuilder.buildAdressDefault().build();
        when(adressRepository.findAll()).thenReturn(AllAdress);
        when(adressRepository.findById(1L)).thenReturn(Optional.ofNullable(adress));

        //Action
        adressService.setPrincipalByIdPerson(1);

        //Result
        verify(adressRepository, times(1)).save(adress);
    }

    @Test
    public void shouldSearchAdresssByPerson() {
        //Data
        Person allPerson = PersonBuilder.buildPersonDefault().build();
        when(personService.findById(1)).thenReturn(allPerson);

        //Action
        adressService.searchAdresssByPerson(1);

        //Result
        verify(adressRepository, times(1)).enderecosPorPessoa(1);
    }
}

package com.aleDev.TesteTecnico.controller;

import com.aleDev.TesteTecnico.v1.controller.ímpl.PersonControllerImpl;
import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new PersonControllerImpl(personService))
                .build();
    }

    @Test
    public void testFindById() throws Exception {
        Integer id = 1;
        Person person = new Person(id, "John Doe", "2005-10-12");

        when(personService.findById(id)).thenReturn(person);

        MvcResult result = mockMvc.perform(get("/v1/pessoas/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"John Doe\",\"birthDate\":\"2005-10-12\"}")))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Person responsePerson = new ObjectMapper().readValue(responseBody, Person.class);
        assertEquals(person, responsePerson);
    }

    @Test
    public void testFindAll() throws Exception {
        Person person1 = new Person(1, "John Doe", "1986-12-19");
        Person person2 = new Person(2, "Jane Smith", "1989-05-22");
        List<Person> persons = Arrays.asList(person1, person2);

        when(personService.findAll()).thenReturn(persons);

        MvcResult result = mockMvc.perform(get("/v1/pessoas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[0].birthDate", is("1986-12-19")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")))
                .andExpect(jsonPath("$[1].birthDate", is("1989-05-22")))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<Person> responsePersons = new ObjectMapper().readValue(responseBody, new TypeReference<List<Person>>() {
        });
        assertEquals(persons, responsePersons);
    }
    @Test
    public void testSaveNewPerson() {
        // Criar a pessoa a ser salva
        Person person = new Person();
        person.setName("John Doe");
        person.setBirthDate("1955-05-15");

        // Criar o mock do serviço de pessoa
        PersonService personServiceMock = mock(PersonService.class);

        // Criar o mock da resposta do serviço de pessoa
        Person savedPerson = new Person();
        savedPerson.setId(1);
        savedPerson.setName("John Doe");
        savedPerson.setBirthDate("1955-05-15");
        when(personServiceMock.saveNewPerson(person)).thenReturn(savedPerson);

        // Criar o controller com o mock do serviço de pessoa
        PersonControllerImpl personController = new PersonControllerImpl(personServiceMock);

        // Chamar o método para salvar a pessoa
        ResponseEntity<Person> response = personController.saveNewPerson(person);

        // Verificar se o status de resposta é OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar se a pessoa retornada é a mesma que foi salva
        assertEquals(savedPerson, response.getBody());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        // Criação dos mocks
        Integer id = 1;
        Person person = new Person(id, "John Doe", "2000-01-01");
        Person updatedPerson = new Person(id, "Jane Doe", "2001-01-01");

        // Mockando o comportamento do método updatePerson
        when(personService.updatePerson(eq(id), ArgumentMatchers.any(Person.class))).thenReturn(updatedPerson);

        // Execução do teste
        String requestBody = "{\"name\":\"Jane Doe\",\"birthDate\":\"2001-01-01\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/pessoas/{id}", id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Verificação do resultado
        verify(personService, times(1)).updatePerson(eq(id),ArgumentMatchers.any(Person.class));
    }
}

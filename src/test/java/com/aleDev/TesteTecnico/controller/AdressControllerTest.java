package com.aleDev.TesteTecnico.controller;

import com.aleDev.TesteTecnico.builder.PersonBuilder;
import com.aleDev.TesteTecnico.v1.controller.ímpl.AdressControllerImpl;
import com.aleDev.TesteTecnico.v1.entity.Adress;
import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.service.AdressService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class AdressControllerTest {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @Mock
    private AdressService adressService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AdressControllerImpl(adressService))
                .build();
    }

    @Test
    public void testSaveNewAdress() {
        // Criar o Endereço a ser salvo
        Adress adress = new Adress();
        adress.setStreet("Rua X");
        adress.setNumber("123");
        adress.setCity("São Paulo");
        adress.setZipCode("01234-567");


        // Criar o mock do serviço de adress
        AdressService adressServiceMock = mock(AdressService.class);

        // Criar o mock da resposta do serviço de adress
        Adress savedAdress = new Adress();
        savedAdress.setId(1L);
        savedAdress.setStreet(adress.getStreet());
        savedAdress.setNumber(adress.getNumber());
        savedAdress.setCity(adress.getCity());
        savedAdress.setZipCode(adress.getZipCode());
        when(adressServiceMock.saveNewAdress(adress)).thenReturn(savedAdress);

        // Criar o controller com o mock do serviço de adress
        AdressControllerImpl adressController = new AdressControllerImpl(adressServiceMock);

        // Chamar o método para salvar o adress
        ResponseEntity<Adress> response = adressController.saveNewAdress(adress);

        // Verificar se o status de resposta é OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar se a adress retornada é o mesmo que foi salvo
        assertEquals(savedAdress, response.getBody());
    }


    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<Adress> expectedAdresses = Arrays.asList(
                new Adress(1L, "Rua A", "123", "São Paulo", "01234-567"),
                new Adress(2L, "Rua B", "456", "Rio de Janeiro", "23456-789")
        );

        Mockito.when(adressService.findAll()).thenReturn(expectedAdresses);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/enderecos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        List<Adress> responseAdresses = new ObjectMapper().readValue(responseBody, new TypeReference<List<Adress>>() {
        });

        // Assert
        assertEquals(expectedAdresses.size(), responseAdresses.size());
        assertEquals(expectedAdresses.get(0), responseAdresses.get(1));
        assertEquals(expectedAdresses.get(1), responseAdresses.get(0));
        Mockito.verify(adressService, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindById() throws Exception {
        Integer id = 1;
        Person person = PersonBuilder.buildPersonDefault().build();
        Adress adress = new Adress((long)id, "Rua A", "123", "São Paulo", "01234-567", person);

        when(adressService.findById(id)).thenReturn(adress);

        MvcResult result = mockMvc.perform(get("/v1/enderecos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Adress responseAdress = new ObjectMapper().readValue(responseBody, Adress.class);

        assertEquals(adress.getId(), responseAdress.getId());
        assertEquals(adress.getStreet(), responseAdress.getStreet());
        assertEquals(adress.getNumber(), responseAdress.getNumber());
        assertEquals(adress.getCity(), responseAdress.getCity());
        assertEquals(adress.getZipCode(), responseAdress.getZipCode());
        assertEquals(adress.getPerson(), responseAdress.getPerson());
    }


    @Test
    public void testSearchAdresssByPerson() throws Exception {
        // Criação dos objetos de teste
        Integer personId = 1;
        List<Adress> expectedAdresses = new ArrayList<>();
        Person person = new Person(personId, "João da Silva", "2000-12-25");
        expectedAdresses.add(new Adress(1L, "São Paulo", "Rua A", "91796070", "123", true, person));

        // Configuração do comportamento do serviço de endereços
        when(adressService.searchAdresssByPerson(personId)).thenReturn(expectedAdresses);

        // Execução da requisição HTTP
        mockMvc.perform(get("/v1/enderecos/person/{id}", personId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].city", is("São Paulo")))
                .andExpect(jsonPath("$[0].street", is("Rua A")))
                .andExpect(jsonPath("$[0].number", is("123")))
                .andExpect(jsonPath("$[0].zipCode", is("91796070")))
                .andExpect(jsonPath("$[0].main", is(true)))
                .andExpect(jsonPath("$[0].person.id", is(personId)))
                .andExpect(jsonPath("$[0].person.name", is("João da Silva")))
                .andExpect(jsonPath("$[0].person.birthDate", is("2000-12-25")));
    }

    @Test
    public void testSetPrincipalByIdPerson() {
        // Criando um objeto Adress para retornar do mock do serviço
        Adress dummyAdress = new Adress();
        dummyAdress.setMain(true);

        // Mockando o serviço
        when(adressService.setPrincipalByIdPerson(anyInt())).thenReturn(dummyAdress);

        // Chamando o método do controlador
        Adress resultAdress = adressService.setPrincipalByIdPerson(1);

        // Verificando se o método do serviço foi chamado com o argumento correto
        verify(adressService, times(1)).setPrincipalByIdPerson(1);

        // Verificando se o objeto Adress retornado pelo controlador
        // é o mesmo que foi retornado pelo serviço
        assertSame(dummyAdress, resultAdress);

        // Verificando se o campo "main" do objeto retornado pelo controlador é verdadeiro
        assertTrue(resultAdress.getMain());
    }
}

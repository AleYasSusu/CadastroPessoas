package com.aleDev.TesteTecnico.v1.service.impl;


import com.aleDev.TesteTecnico.v1.entity.Adress;
import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.exception.EntitiesNotFoundException;
import com.aleDev.TesteTecnico.v1.repository.AdressRepository;
import com.aleDev.TesteTecnico.v1.service.AdressService;
import com.aleDev.TesteTecnico.v1.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    private final AdressRepository adressRepository;

    private final PersonService personService;

    @Override
    public List<Adress> findAll() {
        return adressRepository.findAll();
    }

    @Override
    public Adress saveNewAdress(Adress Adress) {
        int pessoaId = Adress.getPerson().getId();

        Person pessoa = personService.findById(pessoaId);

        Adress.setPerson(pessoa);

        return adressRepository.save(Adress);
    }

    @Override
    public Adress setPrincipalByIdPerson(Integer id) {

        List<Adress> Adresss = adressRepository.findAll();

        for (Adress Adress : Adresss) {
            Adress.setMain(false);
        }

        Adress AdressPrincipal = findById(id);
        AdressPrincipal.setMain(true);

        return adressRepository.save(AdressPrincipal);

    }

    @Override
    public List<Adress> searchAdresssByPerson(Integer id) {
        personService.findById(id);
        return adressRepository.adressByPerson(id);
    }

    @Override
    public Adress findById(Integer id) {
        return adressRepository.findById(id)
                .orElseThrow(() -> new EntitiesNotFoundException(
                        String.format("Endereço com o id %d não encontrado", id)));
    }
}

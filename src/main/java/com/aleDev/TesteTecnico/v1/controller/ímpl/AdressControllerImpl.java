package com.aleDev.TesteTecnico.v1.controller.ímpl;


import com.aleDev.TesteTecnico.v1.controller.AdressController;
import com.aleDev.TesteTecnico.v1.entity.Adress;
import com.aleDev.TesteTecnico.v1.service.AdressService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/enderecos")
@RequiredArgsConstructor
public class AdressControllerImpl implements AdressController {
	
	private final AdressService adressService;
	@Override
	@GetMapping("/{id}")
	public Adress findById(@PathVariable Integer id) {
		return adressService.findById(id);
	}

	@Override
	@GetMapping
	public List<Adress> findAll() {

		return adressService.findAll();
	}

	@Override
	@PostMapping
	public ResponseEntity<Adress> saveNewAdress(Adress adress) {
		try {
			Adress savedAdress = adressService.saveNewAdress(adress);
			return ResponseEntity.ok(savedAdress);
		} catch (DataIntegrityViolationException e) {
			List<Adress> adresses = findAll();
			adress.setId((long) (adresses.size() + 1));
			Adress savedAdress = adressService.saveNewAdress(adress);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedAdress);
		}
	}
	@Override
	@GetMapping("/person/{id}")
	public List<Adress> searchAdresssByPerson(Integer id) {

		return adressService.searchAdresssByPerson(id);
	}

	@Override
	@GetMapping("/principal/{id}")
	public Adress setPrincipalByIdPerson(Integer id) {
		return adressService.setPrincipalByIdPerson(id);
		
	}
	
}

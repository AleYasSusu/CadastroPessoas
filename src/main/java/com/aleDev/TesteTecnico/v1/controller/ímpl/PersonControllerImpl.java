package com.aleDev.TesteTecnico.v1.controller.ímpl;


import com.aleDev.TesteTecnico.v1.controller.PersonController;
import com.aleDev.TesteTecnico.v1.entity.Person;
import com.aleDev.TesteTecnico.v1.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pessoas")
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController {

	private final PersonService personService;

	@Override
	@GetMapping("/{id}")
	public Person findById(@PathVariable Integer id) {
		return personService.findById(id);
	}

	@Override
	@GetMapping
	public List<Person> findAll() {
		return personService.findAll();
	}

	@Override
	@PostMapping
	public ResponseEntity<Person> saveNewPerson(Person pessoa) {
		try {
			Person savedPerson = personService.saveNewPerson(pessoa);
			return ResponseEntity.ok(savedPerson);
		} catch (DataIntegrityViolationException e) {
			List<Person> pessoas = findAll();
			pessoa.setId((pessoas.size() + 1));
			Person savedPerson = personService.saveNewPerson(pessoa);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
		}
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
		personService.updatePerson(id, person);
		return ResponseEntity.noContent().build();
	}
}

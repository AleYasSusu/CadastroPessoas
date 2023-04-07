package com.aleDev.TesteTecnico.v1.repository;

import com.aleDev.TesteTecnico.v1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	

}

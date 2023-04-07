package com.aleDev.TesteTecnico.v1.repository;

import java.util.List;

import com.aleDev.TesteTecnico.v1.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long>{

	@Query("from Adress where person.id = :id")
	List<Adress> enderecosPorPessoa(@Param("id") Integer id);
	
}

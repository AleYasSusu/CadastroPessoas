package com.aleDev.TesteTecnico.v1.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Adress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String zipCode;
	
	@Column(nullable = false)
	private String number;

	private Boolean main;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Person person;


    public Adress(long l, String ruaA, String s, String sãoPaulo, String s1) {
    }

	public Adress(Long id, String ruaA, String s, String sãoPaulo, String s1, Person build) {
	}
}

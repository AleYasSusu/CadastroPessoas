package com.aleDev.TesteTecnico.builder;

import com.aleDev.TesteTecnico.v1.entity.Person;

import java.util.Collections;
import java.util.List;

public class PersonBuilder {

        private Person person;

        public static PersonBuilder buildPersonDefault() {
            PersonBuilder builder = new PersonBuilder();
            builder.person = Person.builder()
                    .id(1)
                    .birthDate("2000-12-19")
                    .name("Suelen Da Silva")
                    .build();
            return builder;
        }

        public Person build() {
            return person;
        }

        public List<Person> buildList() {
            return Collections.singletonList(person);
        }
    }

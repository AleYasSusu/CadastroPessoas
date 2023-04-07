package com.aleDev.TesteTecnico.builder;

import com.aleDev.TesteTecnico.v1.entity.Adress;

import java.util.Collections;
import java.util.List;

public class AdressBuilder {

    private Adress adress;

    public static AdressBuilder buildAdressDefault() {
        AdressBuilder builder = new AdressBuilder();
        builder.adress = Adress.builder()
                .id(1)
                .city("Porto Alegre")
                .main(true)
                .zipCode("91796070")
                .number("6436")
                .person(PersonBuilder.buildPersonDefault().build())
                .street("Av. João Antonio da Silveira")
                .build();
        return builder;
    }

    public Adress build() {
        return adress;
    }

    public List<Adress> buildList() {
        return Collections.singletonList(adress);
    }
}

package com.example.testetecnico.v1.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "Mensagem", description = "Mensagem contendo a descrição do erro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @ApiModelProperty(value = "Mensagem descritiva do erro", readOnly = true)
    private String mensagem;
}

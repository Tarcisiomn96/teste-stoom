package com.stoom.produtos.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponseDto {

    private Long id;

    private String codigo;

    private String nome;

    private boolean ativa;

}
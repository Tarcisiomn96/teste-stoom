package com.stoom.produtos.util;

import lombok.Getter;

@Getter
public enum StatusEnum {

    ATIVO("Ativo", true),
    INATIVO("Inativo",false);

    private boolean valor;
    private String descricao;

    StatusEnum(String descricao, boolean valor) {
        this.valor = valor;
        this.descricao = descricao;
    }

}

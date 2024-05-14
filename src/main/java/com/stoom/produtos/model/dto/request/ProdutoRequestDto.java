package com.stoom.produtos.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProdutoRequestDto {

    private Long id;

    private String codigo;

    private String nome;

    private String descricao;

    private List<Long> idsCategoria;

    private Long idMarca;

    private BigDecimal preco;
}

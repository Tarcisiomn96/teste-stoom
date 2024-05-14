package com.stoom.produtos.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProdutoResponseDto {

    private Long id;

    private String codigo;

    private String nome;

    private String descricao;

    private List<CategoriaResponseDto> categorias;

    private MarcaResponseDto marca;

    private BigDecimal preco;

    private boolean ativo;

}

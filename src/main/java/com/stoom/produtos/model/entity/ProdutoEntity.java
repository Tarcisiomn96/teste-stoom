package com.stoom.produtos.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "PRODUTO")
@Getter
@Setter
public class ProdutoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToOne()
    @JoinColumn(name = "ID_MARCA")
    private MarcaEntity marca;

    @ManyToMany()
    @JoinTable( name="PRODUTO_CATEGORIA",
                joinColumns= {@JoinColumn(name="ID_PRODUTO")},
                inverseJoinColumns= {@JoinColumn(name="ID_CATEGORIA")})
    private Set<CategoriaEntity> categorias;

    private BigDecimal preco;

    private boolean ativo;

}
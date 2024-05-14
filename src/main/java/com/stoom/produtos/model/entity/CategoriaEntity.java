package com.stoom.produtos.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CATEGORIA")
@Getter
@Setter
public class CategoriaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nome;

    private boolean ativa;

    @ManyToMany(mappedBy = "categorias")
    private Set<ProdutoEntity> produtos;

}

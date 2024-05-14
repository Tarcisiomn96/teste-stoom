package com.stoom.produtos.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "MARCA")
@Getter
@Setter
public class MarcaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String codigo;

    private String nome;

    private boolean ativa;

}

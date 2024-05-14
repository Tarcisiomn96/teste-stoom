package com.stoom.produtos.mapper;

import com.stoom.produtos.model.dto.response.ProdutoResponseDto;
import com.stoom.produtos.model.entity.CategoriaEntity;
import com.stoom.produtos.model.entity.MarcaEntity;
import com.stoom.produtos.model.entity.ProdutoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    private CategoriaMapper categoriaMapper;
    private MarcaMapper marcaMapper;

    @Autowired
    public ProdutoMapper(CategoriaMapper categoriaMapper, MarcaMapper marcaMapper) {
        this.categoriaMapper = categoriaMapper;
        this.marcaMapper = marcaMapper;
    }

    public ProdutoResponseDto entityToResponseDto(ProdutoEntity produto){

        if(produto == null){
            return null;
        }

        ProdutoResponseDto responseDto = new ProdutoResponseDto();

        responseDto.setId(produto.getId());
        responseDto.setCodigo(produto.getCodigo());
        responseDto.setNome(produto.getNome());
        responseDto.setDescricao(produto.getDescricao());

        Set<CategoriaEntity> categorias = produto.getCategorias();

        if(categorias != null && !categorias.isEmpty()){
            responseDto.setCategorias(categorias.stream().map(categoriaMapper::entityToResponseDto).collect(Collectors.toList()));
        }

        MarcaEntity marca = produto.getMarca();

        if(marca != null){
            responseDto.setMarca(marcaMapper.entityToResponseDto(produto.getMarca()));
        }

        responseDto.setPreco(produto.getPreco());
        responseDto.setAtivo(produto.isAtivo());

        return responseDto;
    }
}

package com.stoom.produtos.mapper;

import com.stoom.produtos.model.dto.response.CategoriaResponseDto;
import com.stoom.produtos.model.dto.response.ProdutoResponseDto;
import com.stoom.produtos.model.entity.CategoriaEntity;
import com.stoom.produtos.model.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaResponseDto entityToResponseDto(CategoriaEntity categoria){

        if(categoria == null){
            return null;
        }

        CategoriaResponseDto responseDto = new CategoriaResponseDto();

        responseDto.setId(categoria.getId());
        responseDto.setCodigo(categoria.getCodigo());
        responseDto.setNome(categoria.getNome());
        responseDto.setAtiva(categoria.isAtiva());

        return responseDto;
    }
}

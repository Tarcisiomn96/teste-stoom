package com.stoom.produtos.mapper;

import com.stoom.produtos.model.dto.response.MarcaResponseDto;
import com.stoom.produtos.model.entity.MarcaEntity;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {

    public MarcaResponseDto entityToResponseDto(MarcaEntity marca){

        if(marca == null){
            return null;
        }

        MarcaResponseDto responseDto = new MarcaResponseDto();

        responseDto.setId(marca.getId());
        responseDto.setCodigo(marca.getCodigo());
        responseDto.setNome(marca.getNome());
        responseDto.setAtiva(marca.isAtiva());

        return responseDto;
    }
}

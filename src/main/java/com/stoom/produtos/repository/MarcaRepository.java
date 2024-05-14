package com.stoom.produtos.repository;

import com.stoom.produtos.model.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Long> {
}

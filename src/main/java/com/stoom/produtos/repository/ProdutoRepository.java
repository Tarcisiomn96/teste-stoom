package com.stoom.produtos.repository;

import com.stoom.produtos.model.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query(" SELECT P FROM ProdutoEntity P "
            + " LEFT JOIN P.categorias C "
            + " LEFT JOIN P.marca M "
            + " WHERE (:ativo IS NULL OR P.ativo = :ativo) "
            + " AND (:marcaAtiva IS NULL OR M.ativa = :marcaAtiva) "
            + " AND (:categoriaAtiva IS NULL OR C.ativa = :categoriaAtiva) "
            + " AND (:codigoMarca IS NULL OR M.codigo = :codigoMarca) "
            + " AND (:CodigoCategoria IS NULL OR C.codigo = :CodigoCategoria) "
            + " AND (:codigo IS NULL OR P.codigo = :codigo) "
            + " AND (:nome IS NULL OR lower(P.nome) like lower(concat('%', :nome, '%')) ) "
            + " AND (:descricao IS NULL OR lower(P.descricao) like lower(concat('%', :descricao, '%'))) ")
    List<ProdutoEntity> listarPorFiltros(@Param("ativo") Boolean ativo,
                                         @Param("marcaAtiva")Boolean marcaAtiva,
                                         @Param("categoriaAtiva")Boolean categoriaAtiva,
                                         @Param("codigoMarca")String codigoMarca,
                                         @Param("CodigoCategoria")String CodigoCategoria,
                                         @Param("codigo")String codigo,
                                         @Param("nome")String nome,
                                         @Param("descricao")String descricao);

}

package com.stoom.produtos.service;

import com.stoom.produtos.mapper.ProdutoMapper;
import com.stoom.produtos.model.dto.request.ProdutoRequestDto;
import com.stoom.produtos.model.dto.response.ProdutoResponseDto;
import com.stoom.produtos.model.entity.CategoriaEntity;
import com.stoom.produtos.model.entity.ProdutoEntity;
import com.stoom.produtos.repository.CategoriaRepository;
import com.stoom.produtos.repository.MarcaRepository;
import com.stoom.produtos.repository.ProdutoRepository;
import com.stoom.produtos.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoService {

    private ProdutoRepository repository;
    private ProdutoMapper mapper;

    private CategoriaRepository categoriaRepository;

    private MarcaRepository marcaRepository;

    @Autowired
    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper, CategoriaRepository categoriaRepository, MarcaRepository marcaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
    }

    public ProdutoResponseDto salvar(ProdutoRequestDto produtoRequestDto){

        if(produtoRequestDto == null){
            return null;
        }

        ProdutoEntity produto = criar(produtoRequestDto);

        repository.save(produto);

        return mapper.entityToResponseDto(produto);

    }

    public ProdutoResponseDto atualizar(Long id, ProdutoRequestDto produtoRequestDto){

        if(produtoRequestDto == null || id == null){
            return null;
        }

        ProdutoEntity produto = getProdutoEntity(id);

        if(produto == null){
            return null;
        }

        atualizar(produtoRequestDto, produto);

        repository.save(produto);

        return mapper.entityToResponseDto(produto);

    }

    public ProdutoResponseDto buscarPorId(Long id){

        if(id == null){
            return null;
        }

        ProdutoEntity produto = getProdutoEntity(id);

        if (produto == null) {
            return null;
        }

        return mapper.entityToResponseDto(produto);
    }

    private ProdutoEntity getProdutoEntity(Long id) {

        Optional<ProdutoEntity> produtoOptional = repository.findById(id);

        if(!produtoOptional.isPresent()){
            return null;
        }

        ProdutoEntity produto = produtoOptional.get();
        return produto;
    }

    public List<ProdutoResponseDto> listarPorFiltros(StatusEnum ativo,
                                                     StatusEnum marcaAtiva,
                                                     StatusEnum categoriaAtiva,
                                                     String codigoMarca,
                                                     String codigoCategoria,
                                                     String codigo,
                                                     String nome,
                                                     String descricao){

        List<ProdutoEntity> produtos = repository.listarPorFiltros( ativo != null ? ativo.isValor() : null,
                                                                    marcaAtiva != null ? marcaAtiva.isValor() : null,
                                                                    categoriaAtiva != null ? categoriaAtiva.isValor() : null,
                                                                    codigoMarca,
                                                                    codigoCategoria,
                                                                    codigo,
                                                                    nome,
                                                                    descricao);

        return produtos.stream().map(mapper::entityToResponseDto).collect(Collectors.toList());

    }

    public void deletar(Long id){

        if(id == null){
            return;
        }

        repository.deleteById(id);

    }

    public ProdutoResponseDto inativar(Long id){

        if(id == null){
            return null;
        }

        ProdutoEntity produto = getProdutoEntity(id);

        if (produto == null) {
            return null;
        }

        produto.setAtivo(false);

        repository.save(produto);

        return mapper.entityToResponseDto(produto);

    }

    private ProdutoEntity criar(ProdutoRequestDto produtoRequestDto) {

        ProdutoEntity produto = new ProdutoEntity();

        preencherDados(produtoRequestDto, produto);

        return produto;

    }

    private void atualizar(ProdutoRequestDto produtoRequestDto, ProdutoEntity produto) {

        preencherDados(produtoRequestDto, produto);

    }

    private void preencherDados(ProdutoRequestDto produtoRequestDto, ProdutoEntity produto) {

        produto.setCodigo(produtoRequestDto.getCodigo());
        produto.setNome(produtoRequestDto.getNome());
        produto.setDescricao(produtoRequestDto.getDescricao());
        produto.setPreco(produtoRequestDto.getPreco());
        produto.setAtivo(true);

        List<Long> idsCategoria = produtoRequestDto.getIdsCategoria();

        if(idsCategoria == null || idsCategoria.isEmpty()){

            produto.setCategorias(null);

        } else{

            List<CategoriaEntity> categorias = categoriaRepository.findAllById(idsCategoria);

            produto.setCategorias(categorias.stream().collect(Collectors.toSet()));

        }

        Long idMarca = produtoRequestDto.getIdMarca();

        if(idMarca != null){
            marcaRepository.findById(idMarca).ifPresent(marca -> produto.setMarca(marca));
        }else{
            produto.setMarca(null);
        }
    }

}

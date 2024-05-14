package com.stoom.produtos.controller;

import com.stoom.produtos.model.dto.request.ProdutoRequestDto;
import com.stoom.produtos.model.dto.response.ProdutoResponseDto;
import com.stoom.produtos.service.ProdutoService;
import com.stoom.produtos.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ProdutoResponseDto> salvar(@RequestBody ProdutoRequestDto produtoRequestDto){

        ProdutoResponseDto produto = service.salvar(produtoRequestDto);

        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.badRequest().build();

    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProdutoResponseDto> atualizar(@RequestBody ProdutoRequestDto produtoRequestDto, @PathVariable("id") Long id){

        ProdutoResponseDto produto = service.atualizar(id, produtoRequestDto);

        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.badRequest().build();

    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable("id") Long id){

        ProdutoResponseDto produto = service.buscarPorId(id);

        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/listar-por-filtros", produces = "application/json")
    public ResponseEntity<List<ProdutoResponseDto>> listarPorFiltros(@RequestParam(name = "ativo", required = false) StatusEnum ativo,
                                                                     @RequestParam(name = "marcaAtiva", required = false) StatusEnum marcaAtiva,
                                                                     @RequestParam(name = "categoriaAtiva", required = false) StatusEnum categoriaAtiva,
                                                                     @RequestParam(name = "codigoMarca", required = false) String codigoMarca,
                                                                     @RequestParam(name = "codigoCategoria", required = false) String codigoCategoria,
                                                                     @RequestParam(name = "codigo", required = false) String codigo,
                                                                     @RequestParam(name = "nome", required = false) String nome,
                                                                     @RequestParam(name = "descricao", required = false) String descricao){

        List<ProdutoResponseDto> produtos = service.listarPorFiltros(   ativo,
                                                                        marcaAtiva,
                                                                        categoriaAtiva,
                                                                        codigoMarca,
                                                                        codigoCategoria,
                                                                        codigo,
                                                                        nome,
                                                                        descricao);

        return produtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(produtos);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){

        service.deletar(id);

        return ResponseEntity.accepted().build();

    }

    @PutMapping("/inativar/{id}")
    public ResponseEntity<ProdutoResponseDto> inativar(@PathVariable("id") Long id){

        ProdutoResponseDto produto = service.inativar(id);

        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.badRequest().build();

    }

}

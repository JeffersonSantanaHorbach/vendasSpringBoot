package io.github.JeffersonSantanaHorbach.api.controller;

import io.github.JeffersonSantanaHorbach.domain.entity.Produto;
import io.github.JeffersonSantanaHorbach.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    private final Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody  @Valid Produto produto ){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody  @Valid Produto produto ){
        repository
                .findById(id)
                .map( prod -> {
                    produto.setId(prod.getId());
                    repository.save(produto);
                    return produto;
                }).orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository
                .findById(id)
                .map( prod -> {
                    repository.delete(prod);
                    return Void.TYPE;
                }).orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado."));
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado."));
    }

    @GetMapping
    public List<Produto> find(Produto filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example<Produto> example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }
}
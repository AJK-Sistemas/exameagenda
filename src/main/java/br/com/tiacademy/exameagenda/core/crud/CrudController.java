package br.com.tiacademy.exameagenda.core.crud;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class CrudController<T extends CrudDomain<ID>, D, ID> {

    @Autowired
    protected CrudService<T, ID> service;

    @Autowired
    protected CrudConverter<T, D> converter;

    @GetMapping
    public ResponseEntity<Page<D>> pagination(Pageable pageable) {

        var ListaPaginada = service.pagination(pageable).map(converter::entidadeParaDto);

        return ResponseEntity.ok(ListaPaginada);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<D>> listarTodos() {

        var ListaDto = service.listar().stream().map(converter::entidadeParaDto).collect(Collectors.toList());

        return ResponseEntity.ok(ListaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> porId(@PathVariable("id") ID id) {
        var entidade = service.porId(id);

        if (Objects.isNull(entidade)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(converter.entidadeParaDto(entidade));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody D dto) {

        var entidade = converter.dtoParaEntidade(dto);

        var salvo = service.criar(entidade);

        ServletUriComponentsBuilder buider = ServletUriComponentsBuilder.fromCurrentRequest();

        var uri = buider.path("/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable("id") ID id, @RequestBody D dto) {

        var novaEntidade = converter.dtoParaEntidade(dto);
        
        var salvo = service.editar(id, novaEntidade);

        return ResponseEntity.ok(converter.entidadeParaDto(salvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
    
            service.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
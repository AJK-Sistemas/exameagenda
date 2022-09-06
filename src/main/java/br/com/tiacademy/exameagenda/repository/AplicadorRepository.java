package br.com.tiacademy.exameagenda.repository;

import br.com.tiacademy.exameagenda.domain.Aplicador;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;

@Repository
public interface AplicadorRepository extends CrudRepository<Aplicador, Long> {

    List<Aplicador> findByEspecialidade(String especialidade);
    int countByEspecialidade(String especialidade);
}
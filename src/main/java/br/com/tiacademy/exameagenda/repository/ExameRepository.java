package br.com.tiacademy.exameagenda.repository;

import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Exame;

@Repository
public interface ExameRepository extends CrudRepository<Exame, Long>{
    
}

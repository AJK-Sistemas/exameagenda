package br.com.tiacademy.exameagenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Aplicador;

@Repository
public interface AplicadorRepository extends CrudRepository<Aplicador, Long>{
    
    @Query("select a from Aplicador a where a.especialidade = :tipo ")
    List<Aplicador> getAplicadores(@Param("tipo") String tipo);
}
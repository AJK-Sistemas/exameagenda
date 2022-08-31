package br.com.tiacademy.exameagenda.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Exame;

@Repository
public interface ExameRepository extends CrudRepository<Exame, Long>{
    
    @Query("select e.tipo from Exame e where e.id in (:id)")
    String tipoExame(@Param("id") Long id);
}

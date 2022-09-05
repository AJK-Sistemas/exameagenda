package br.com.tiacademy.exameagenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Exame;

@Repository
public interface ExameRepository extends CrudRepository<Exame, Long>{
    
    @Query("select e.tipo from Exame e where e.id = :id ")
    String tipoExame(@Param("id") Long id);

    @Query("select e from Exame e where e.tipo = :tipo ")
    List<Exame> exame(@Param("tipo") String tipo);
}

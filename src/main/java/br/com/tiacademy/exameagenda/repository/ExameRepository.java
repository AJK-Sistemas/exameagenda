package br.com.tiacademy.exameagenda.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Exame;

@Repository
public interface ExameRepository extends CrudRepository<Exame, Long> {

    @Query("select e from Exame e inner join Agendamento a on a.exame = e.id and a.dataRetirada in (:data) and a.status in (:status) ")
    List<Exame> exames(@Param("status") String status, @Param("data") Date data);

}

package br.com.tiacademy.exameagenda.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.domain.Aplicador;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

    @Query("select a from Agendamento a where a.dataExame in (:data)")
    List<Agendamento> dataExame(@Param("data") Date data);

    @Query("select a from Agendamento a where a.horaExame in (:hora)")
    List<Agendamento> horaExame(@Param("hora") Time hora);

    @Query("select e.tipo from Exame e where e.id in (:id)")
    String tipoExame(@Param("id") Long id);

    @Query("select a.id from Aplicador a where a.especialidade in (:tipo)")
    Long idAplicador(@Param("tipo") String tipo);

    @Query("select a from Agendamento a where a.status in (:status) and a.dataRetirada in (:data)")
    List<Agendamento> listagemDiaria(@Param("status") String status, @Param("data") Date data);

    @Query("select a.aplicador from Agendamento a where a.dataExame = :data and a.horaExame = :hora and a.exame.tipo = :tipo ")
    List<Aplicador> aplicadoresOcupados(@Param("data") Date data, @Param("hora") Time hora, @Param("tipo") String tipo);

}
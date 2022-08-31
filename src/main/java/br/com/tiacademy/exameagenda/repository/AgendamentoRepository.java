package br.com.tiacademy.exameagenda.repository;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendaHorasDTO;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
    // @Query(value="select Time(data_exame) as hora, count(data_exame) as conta
    // from agendamento where Date(data_exame)=:datae and exame_id =:id group by
    // data_exame",nativeQuery=true)
    // List<AgendaHorasDTO> porDataExame(String datae, Long id);

    //@Query(value = "SELECT new br.com.tiacademy.exameagenda.dto.AgendaHorasDTO(DATE_FORMAT(g.dataExame, '%H:%i'), count(g.dataExame)) FROM Agendamento g WHERE DATE_FORMAT(g.dataExame,'%Y-%m-%d') =:datae and g.exame.id =:id group by g.dataExame")
    @Query(value = "SELECT new br.com.tiacademy.exameagenda.dto.AgendaHorasDTO(g.horaExame, count(g.horaExame)) FROM Agendamento g WHERE g.dataExame =:datae and g.exame.id =:id group by g.horaExame")
    List<AgendaHorasDTO> porDataExameHorarios(@Param("datae") Date datae,@Param("id") Long id);

    @Query("select a from Agendamento a where a.status =:status and a.dataRetirada=:data")
    List<Agendamento> listagemDiaria(@Param("status") String status, @Param("data") Date data);
    
    List<Agendamento> findByDataExameAndHoraExame(Date data, Time hora);

    //List<Agendamento> findByDataExameBetween(LocalDateTime startDay, LocalDateTime endDay);

    //List<Agendamento> findByDataRetirada(LocalDate day);

    @Query("select a.id from Aplicador a where a.especialidade in (:tipo)")
    Long idAplicador(@Param("tipo") String tipo);

    @Query("select a from Agendamento a where a.dataExame = (:data)")
    List<Agendamento> dataExame(@Param("data") Date data);

}
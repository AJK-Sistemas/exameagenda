package br.com.tiacademy.exameagenda.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "SELECT new br.com.tiacademy.exameagenda.dto.AgendaHorasDTO(DATE_FORMAT(g.dataExame, '%H:%i'), count(g.dataExame)) FROM Agendamento g WHERE DATE_FORMAT(g.dataExame,'%Y-%m-%d') =:datae and g.exame.id =:id group by g.dataExame")
    List<AgendaHorasDTO> porDataExameHorarios(String datae, Long id);

    List<Agendamento> findByDataExame(LocalDateTime data);

    List<Agendamento> findByDataExameBetween(LocalDateTime startDay, LocalDateTime endDay);

    List<Agendamento> findByDataRetirada(LocalDate day);
}
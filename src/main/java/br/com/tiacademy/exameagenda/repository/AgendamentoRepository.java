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
    // from agendamento where Date(data_exame)=:data and exame_id =:id group by
    // data_exame",nativeQuery=true)
    // List<AgendaHorasDTO> porDataExame(String data, Long id);

    // @Query(value = "SELECT new
    // br.com.tiacademy.exameagenda.dto.AgendaHorasDTO(DATE_FORMAT(g.dataExame,
    // '%H:%i'), count(g.dataExame)) FROM Agendamento g WHERE
    // DATE_FORMAT(g.dataExame,'%Y-%m-%d') =:data and g.exame.id =:id group by
    // g.dataExame")
    @Query(value = "SELECT new br.com.tiacademy.exameagenda.dto.AgendaHorasDTO(g.horaExame, count(g.horaExame)) FROM Agendamento g WHERE g.dataExame =:data and g.exame.id =:id group by g.horaExame")
    List<AgendaHorasDTO> porDataExameHorarios(@Param("data") Date data, @Param("id") Long id);

    @Query("select a from Agendamento a where a.status <> 'Retirado' and a.status <> 'A Fazer' and a.dataRetirada=:data")
    List<Agendamento> listagemDiaria(@Param("data") Date data);

    List<Agendamento> findByDataExameAndHoraExame(Date data, Time hora);

    @Query("select a.id from Aplicador a where a.especialidade = :tipo ")
    Long idAplicador(@Param("tipo") String tipo);

    @Query("select a from Agendamento a where a.dataExame = :data and a.status = 'A Fazer' ")
    List<Agendamento> dataExame(@Param("data") Date data);

    @Query("select a from Agendamento a where a.status = 'A Fazer' and a.dataRetirada = :data ")
    List<Agendamento> aFazer(@Param("data") Date data);

    @Query("select a from Agendamento a where a.status = 'Aguardando Retirada' and a.dataRetirada = :data ")
    List<Agendamento> aRetirar(@Param("data") Date data);
}
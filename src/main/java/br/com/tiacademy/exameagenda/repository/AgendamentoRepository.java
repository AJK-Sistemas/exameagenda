package br.com.tiacademy.exameagenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendaHorasDTO;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long>{
    @Query(value="select Time(data_exame) as hora, count(data_exame) as conta  from agendamento where Date(data_exame)=:datae and exame_id =:id group by data_exame",nativeQuery=true)
    List<AgendaHorasDTO> porDataExame(String datae, Long id);

}
package br.com.tiacademy.exameagenda.repository;

import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Agendamento;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long>{
    
}
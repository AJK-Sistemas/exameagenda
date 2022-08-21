package br.com.tiacademy.exameagenda.repository;

import org.springframework.stereotype.Repository;

import br.com.tiacademy.exameagenda.core.crud.CrudRepository;
import br.com.tiacademy.exameagenda.domain.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long>{
    
}

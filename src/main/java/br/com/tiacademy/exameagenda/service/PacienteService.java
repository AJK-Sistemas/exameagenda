package br.com.tiacademy.exameagenda.service;

import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Paciente;
@Service
public class PacienteService extends CrudService<Paciente, Long> {
	
	@Override
	protected Paciente editarEntidade(Paciente recuperado, Paciente entidade) {
		recuperado.setNome(entidade.getNome());
		recuperado.setCpf(entidade.getCpf());
		recuperado.setTelefone(entidade.getTelefone());
		recuperado.setEmail(entidade.getEmail());
		return recuperado;
	}

	public Boolean exist(Long id) {
		return repository.existsById(id);
	}

}

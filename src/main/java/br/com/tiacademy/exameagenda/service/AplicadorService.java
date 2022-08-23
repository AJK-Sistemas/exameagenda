package br.com.tiacademy.exameagenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.repository.AplicadorRepository;

@Service
public class AplicadorService extends CrudService<Aplicador, Long>{
    @Autowired
	AplicadorRepository apliRepo;
	
	@Override
	protected Aplicador editarEntidade(Aplicador recuperado, Aplicador entidade) {
		recuperado.setNome(entidade.getNome());
		recuperado.setCpf(entidade.getCpf());
		recuperado.setTelefone(entidade.getTelefone());
		recuperado.setEmail(entidade.getEmail());
		recuperado.setEspecialidade(entidade.getEspecialidade());
		return recuperado;
	}

	protected List<Aplicador> porEspecialidade(String especialidade){
		List<Aplicador> aplicadores = apliRepo.findByEspecialidade(especialidade);
		return aplicadores;
	}
}

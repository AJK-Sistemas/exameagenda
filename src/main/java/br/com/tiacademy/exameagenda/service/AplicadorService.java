package br.com.tiacademy.exameagenda.service;

import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Aplicador;

@Service
public class AplicadorService extends CrudService<Aplicador, Long>{
    @Override
	protected Aplicador editarEntidade(Aplicador recuperado, Aplicador entidade) {
		recuperado.setNome(entidade.getNome());
		recuperado.setCpf(entidade.getCpf());
		recuperado.setTelefone(entidade.getTelefone());
		recuperado.setEmail(entidade.getEmail());
		recuperado.setEspecialidade(entidade.getEspecialidade());
		return recuperado;
	}
}

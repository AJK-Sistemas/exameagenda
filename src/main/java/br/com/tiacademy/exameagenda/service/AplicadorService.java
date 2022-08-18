package br.com.tiacademy.exameagenda.service;

import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Aplicador;

@Service
public class AplicadorService extends CrudService<Aplicador, Long>{
    @Override
	protected Aplicador editarEntidade(Aplicador recuperado, Aplicador entidade) {
		recuperado.setNome(entidade.getNome());
		return recuperado;
	}
}

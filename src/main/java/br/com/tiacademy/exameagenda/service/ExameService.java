package br.com.tiacademy.exameagenda.service;

import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Exame;

@Service
public class ExameService extends CrudService<Exame, Long>{
    @Override
	protected Exame editarEntidade(Exame recuperado, Exame entidade) {
		recuperado.setTipo(entidade.getTipo());
		return recuperado;
	}
}

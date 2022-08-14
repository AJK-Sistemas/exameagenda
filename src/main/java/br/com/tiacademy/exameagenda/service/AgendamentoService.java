package br.com.tiacademy.exameagenda.service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Agendamento;

public class AgendamentoService extends CrudService<Agendamento, Long>{
    @Override
	protected Agendamento editarEntidade(Agendamento recuperado, Agendamento entidade) {
		recuperado.setDataExame(entidade.getDataExame());
		return recuperado;
	}
}

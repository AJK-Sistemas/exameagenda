package br.com.tiacademy.exameagenda.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.repository.AgendamentoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AgendamentoService extends CrudService<Agendamento, Long> {

	private final AgendamentoRepository agendamentoRepository;

	@Override
	protected Agendamento editarEntidade(Agendamento recuperado, Agendamento entidade) {

		recuperado.setDataExame(entidade.getDataExame());
		recuperado.setHoraExame(entidade.getHoraExame());
		recuperado.setDataRetirada(entidade.getDataRetirada());
		recuperado.setStatus(entidade.getStatus());
		recuperado.setPaciente(entidade.getPaciente());
		recuperado.setExame(entidade.getExame());
		recuperado.setAplicador(entidade.getAplicador());

		return recuperado;
	}

	public List<Agendamento> getAgendamento() {

		return repository.findAll();
	}

	public List<Agendamento> getByDataExame(Date data) {

		return agendamentoRepository.dataExame(data);
	}

	public List<Agendamento> getByHoraExame(Time time) {

		return agendamentoRepository.horaExame(time);
	}

	public List<Agendamento> getListagemDiaria(String status, Date data) {

		return agendamentoRepository.listagemDiaria(status, data);
	}

}

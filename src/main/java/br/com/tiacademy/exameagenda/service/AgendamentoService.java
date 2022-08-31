package br.com.tiacademy.exameagenda.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.domain.Paciente;
import br.com.tiacademy.exameagenda.dto.AgendaHorasDTO;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.repository.AgendamentoRepository;

@Service
public class AgendamentoService extends CrudService<Agendamento, Long> {
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

	@Autowired
	protected PacienteService pacienteService;

	@Autowired
	protected ExameService exameService;

	@Autowired
	protected AplicadorService aplicadorService;

	@Autowired
	protected AgendamentoRepository agendamentoRepository;

	@Override
	public Agendamento criar(Agendamento entidade) {
		if (entidade.getPaciente().getId() == null) {
			Paciente salvoPaciente = pacienteService.criar(entidade.getPaciente());
			entidade.setPaciente(salvoPaciente);
		}
		return repository.save(entidade);
	}

	public List<Time> geraHoras(Long id, Date data) {
		List<Time> horas = new ArrayList<>();
		List<AgendaHorasDTO> horasAgendadas = agendamentoRepository.porDataExameHorarios(data, id);

		Exame exame = exameService.porId(id);
		if (exame == null)
			return horas;
		Time horaInicio = exame.getHoraInicio();
		Time horaFim = exame.getHoraFim();
		Time intervalo = Time.valueOf(exame.getDuracao());
		Long disponibilidade = exame.getDisponibilidade();
		List<Aplicador> aplicadores = aplicadorService.porEspecialidade(exame.getTipo());
		int totalAplicadores = aplicadores.size();
		Time controle = horaInicio;

		horas.add(horaInicio);

		while (controle.before(horaFim)) {
			Time temp = Time.valueOf(controle.toLocalTime().plusHours(intervalo.toLocalTime().getHour()).plusMinutes(intervalo.toLocalTime().getMinute()));
			if (temp.after(horaFim))
				break;
			controle = temp;
			horas.add(temp);
		}

		List<Time> espelho = horas.stream().filter(s -> {
			Boolean retorno = true;
			List<Boolean> bolList = new ArrayList<>();
			bolList.add(retorno);
			horasAgendadas.forEach(hora -> {
				Long contagem = hora.getConta();
				if (s.toString().equals(hora.getHora().toString()) && (contagem >= disponibilidade || contagem >= totalAplicadores)) {
					bolList.set(0, false);
				}
			});

			return bolList.get(0);
		}).collect(Collectors.toList());
		return espelho;
	}

	// public List<Agendamento> agendamentosData(String data) {
		
	// 	List<Agendamento> agendamentos=agendamentoRepository.findByDataExameBetween(LocalDateTime.parse(data+"T00:00:00"),LocalDateTime.parse(data+"T23:59:59"));

	// 	return agendamentos;
	// }

	// public List<Agendamento> retiradasData(String data) {
		
	// 	List<Agendamento> agendamentos=agendaRepository.findByDataRetirada(LocalDate.parse(data));

	// 	return agendamentos;
	// }

	public List<Agendamento> getListagemDiaria(String status, Date data) {

		return agendamentoRepository.listagemDiaria(status, data);
	}

	public Long idAplicador(String tipo){
		return agendamentoRepository.idAplicador(tipo);
	}

	public List<Agendamento> getAgendamento() {

		return repository.findAll();
	}

	public List<Agendamento> getByDataExame(Date data) {

		return agendamentoRepository.dataExame(data);
	}

}

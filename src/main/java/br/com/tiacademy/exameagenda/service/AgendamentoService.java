package br.com.tiacademy.exameagenda.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import br.com.tiacademy.exameagenda.repository.AgendamentoRepository;

@Service
public class AgendamentoService extends CrudService<Agendamento, Long> {
	@Override
	protected Agendamento editarEntidade(Agendamento recuperado, Agendamento entidade) {
		recuperado.setDataExame(entidade.getDataExame());
		recuperado.setDataRetirada(entidade.getDataRetirada());
		recuperado.setStatus(entidade.getStatus());
		return recuperado;
	}

	@Autowired
	protected PacienteService pacienteService;

	@Autowired
	protected ExameService exameService;

	@Autowired
	protected AplicadorService aplicadorService;

	@Autowired
	protected AgendamentoRepository agendaRepository;

	@Override
	public Agendamento criar(Agendamento entidade) {
		if (entidade.getPaciente().getId() == null) {
			Paciente salvoPaciente = pacienteService.criar(entidade.getPaciente());
			entidade.setPaciente(salvoPaciente);
		}
		return repository.save(entidade);
	}

	public List<String> geraHoras(Long id, String data) {
		List<String> horas = new ArrayList<>();
		List<AgendaHorasDTO> horasAgendadas = agendaRepository.porDataExameHorarios(data, id);

		Exame exame = exameService.porId(id);
		if (exame == null)
			return horas;
		LocalTime horaInicio = LocalTime.parse(exame.getHora_inicio());
		LocalTime horaFim = LocalTime.parse(exame.getHora_fim());
		LocalTime intervalo = LocalTime.parse(exame.getDuracao());
		Long disponibilidade = exame.getDisponibilidade();
		List<Aplicador> aplicadores = aplicadorService.porEspecialidade(exame.getTipo());
		int totalAplicadores = aplicadores.size();
		LocalTime controle = horaInicio;

		horas.add(horaInicio.format(DateTimeFormatter.ofPattern("HH:mm")));

		while (controle.isBefore(horaFim)) {
			LocalTime temp = controle.plusHours(intervalo.getHour()).plusMinutes(intervalo.getMinute());
			if (temp.isAfter(horaFim))
				break;
			controle = temp;
			horas.add(temp.toString());
		}

		List<String> espelho = horas.stream().filter(s -> {
			Boolean retorno = true;
			List<Boolean> bolList = new ArrayList<>();
			bolList.add(retorno);
			horasAgendadas.forEach(hora -> {
				Long contagem = hora.getConta();
				if (s.equals(hora.getHora()) && (contagem >= disponibilidade || contagem >= totalAplicadores)) {
					bolList.set(0, false);
				}
			});

			return bolList.get(0);
		}).collect(Collectors.toList());
		return espelho;
	}

	public List<Agendamento> agendamentosData(String data) {
		
		List<Agendamento> agendamentos=agendaRepository.findByDataExameBetween(LocalDateTime.parse(data+"T00:00:00"),LocalDateTime.parse(data+"T23:59:59"));

		return agendamentos;
	}

	public List<Agendamento> retiradasData(String data) {
		
		List<Agendamento> agendamentos=agendaRepository.findByDataRetirada(LocalDate.parse(data));

		return agendamentos;
	}

}

package br.com.tiacademy.exameagenda.service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.dto.AgendaHorasDTO;
import br.com.tiacademy.exameagenda.repository.AgendamentoRepository;

@Service
public class AgendamentoService extends CrudService<Agendamento, Long> {

	@Override
	protected Agendamento editarEntidade(Agendamento recuperado, Agendamento entidade) {

		if (entidade.getDataExame() != null) {
			recuperado.setDataExame(entidade.getDataExame());
		}
		if (entidade.getHoraExame() != null) {
			recuperado.setHoraExame(entidade.getHoraExame());
		}

		if (entidade.getDataRetirada() != null) {
			recuperado.setDataRetirada(entidade.getDataRetirada());
		}

		if (entidade.getStatus() != null) {
			recuperado.setStatus(entidade.getStatus());
		}

		if (entidade.getPaciente() != null) {
			recuperado.setPaciente(entidade.getPaciente());
		}

		if (entidade.getExame() != null) {
			recuperado.setExame(entidade.getExame());
		}

		if (entidade.getAplicador() != null) {
			recuperado.setAplicador(entidade.getAplicador());
		}

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

	// @Override
	// public Agendamento criar(Agendamento entidade) {

	// if (entidade.getPaciente().getId() == null) {
	// Paciente salvoPaciente = pacienteService.criar(entidade.getPaciente());
	// entidade.setPaciente(salvoPaciente);
	// }
	// return repository.save(entidade);
	// }

	public List<Time> geraHoras(Long id, LocalDate data) {

		List<Time> horas = new ArrayList<>();
		List<AgendaHorasDTO> horasAgendadas = agendamentoRepository.porDataExameHorarios(data, id);

		Exame exame = exameService.porId(id);
		if (exame == null)
			return horas;
		Time horaInicio = exame.getHoraInicio();
		Time horaFim = exame.getHoraFim();
		Time intervalo = exame.getDuracao();
		Long disponibilidade = exame.getDisponibilidade();
		List<Aplicador> aplicadores = aplicadorService.porEspecialidade(exame.getTipo());
		int totalAplicadores = aplicadores.size();
		Time controle = horaInicio;

		horas.add(horaInicio);

		while (controle.before(horaFim)) {

			Time temp = Time.valueOf(controle.toLocalTime().plusHours(intervalo.toLocalTime().getHour())
					.plusMinutes(intervalo.toLocalTime().getMinute()));
			if (temp.after(horaFim))
				break;
			controle = temp;
			horas.add(temp);
		}

		List<Time> espelho = horas.stream().filter(s -> {

			Boolean retorno = true;
			List<Boolean> bolList = new ArrayList<>();
			bolList.add(retorno);
			if (totalAplicadores==0){
				bolList.set(0, false);
			}
			horasAgendadas.forEach(hora -> {
				Long contagem = hora.getConta();
				if (s.toString().equals(hora.getHora().toString())
						&& (contagem >= disponibilidade || contagem >= totalAplicadores)) {
					bolList.set(0, false);
				}
			});

			return bolList.get(0);
		}).collect(Collectors.toList());
		
		return espelho;
	}

	public List<Agendamento> getAfazer(LocalDate data) {

		return agendamentoRepository.aFazer(data);
	}

	public List<Agendamento> getAretirar(LocalDate data) {

		return agendamentoRepository.aRetirar(data);
	}

	public Long idAplicador(String tipo) {
		return agendamentoRepository.idAplicador(tipo);
	}

	public List<Agendamento> getAgendamento() {

		return repository.findAll();
	}

	public List<Agendamento> getByDataExame(LocalDate data) {

		return agendamentoRepository.dataExame(data);
	}

}

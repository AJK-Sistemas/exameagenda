package br.com.tiacademy.exameagenda.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.repository.AgendamentoRepository;
import br.com.tiacademy.exameagenda.repository.AplicadorRepository;

@Service
public class AplicadorService extends CrudService<Aplicador, Long> {
	@Autowired
	AplicadorRepository apliRepo;

	@Autowired
	AgendamentoRepository agendaRepo;

	@Override
	protected Aplicador editarEntidade(Aplicador recuperado, Aplicador entidade) {
		recuperado.setNome(entidade.getNome());
		recuperado.setCpf(entidade.getCpf());
		recuperado.setTelefone(entidade.getTelefone());
		recuperado.setEmail(entidade.getEmail());
		recuperado.setEspecialidade(entidade.getEspecialidade());
		return recuperado;
	}

	protected List<Aplicador> porEspecialidade(String especialidade) {
		List<Aplicador> aplicadores = apliRepo.findByEspecialidade(especialidade);
		return aplicadores;
	}

	public List<Aplicador> apliDisponiveis(String hora, String data, String especialidade) {

		List<Aplicador> aplicadores = apliRepo.findByEspecialidade(especialidade);

		List<Aplicador> ocupados = agendaRepo.findByDataExame(LocalDateTime.parse(data + "T" + hora)).stream()
				.map(r -> r.getAplicador())
				.filter(f -> f.getEspecialidade().equals(especialidade))
				.collect(Collectors.toList());

		List<Aplicador> disponiveis = aplicadores.stream().filter(a -> !ocupados.contains(a))
				.collect(Collectors.toList());

		return disponiveis;
	}
}

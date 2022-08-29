package br.com.tiacademy.exameagenda.service;

import java.sql.Date;
import java.sql.Time;
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
	private  AplicadorRepository aplicadorRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Override
	protected Aplicador editarEntidade(Aplicador recuperado, Aplicador entidade) {
		recuperado.setNome(entidade.getNome());
		recuperado.setCpf(entidade.getCpf());
		recuperado.setTelefone(entidade.getTelefone());
		recuperado.setEmail(entidade.getEmail());
		recuperado.setEspecialidade(entidade.getEspecialidade());
		return recuperado;
	}

	
	boolean controle = true;

	public List<Aplicador> getAplicador(String tipo, Date data, Time hora) {

		List<Aplicador> aplicadores = aplicadorRepository.getAplicadores(tipo);

		List<Aplicador> aplicadoresOcupados = agendamentoRepository.aplicadoresOcupados(data, hora, tipo);

		List<Aplicador> aplicadoresDisponiveis = aplicadores.stream().filter(ap -> {
			controle = true;
			aplicadoresOcupados.forEach(aplicador -> {

				if (ap.equals(aplicador)) {
					controle = false;
				}
			});
			return controle;
		}).collect(Collectors.toList());

		return aplicadoresDisponiveis;
	}
}

package br.com.tiacademy.exameagenda.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.repository.ExameRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExameService extends CrudService<Exame, Long> {

	@Override
	protected Exame editarEntidade(Exame recuperado, Exame entidade) {
		recuperado.setTipo(entidade.getTipo());
		recuperado.setDescricao(entidade.getDescricao());
		recuperado.setDuracao(entidade.getDuracao());
		recuperado.setDisponibilidade(entidade.getDisponibilidade());
		recuperado.setValor(entidade.getValor());
		return recuperado;
	}

	private final ExameRepository exameRepository;

	public List<Exame> getExames(String status, Date data) {

		return exameRepository.exames(status, data);
	}

}

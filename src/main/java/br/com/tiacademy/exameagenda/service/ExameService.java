package br.com.tiacademy.exameagenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.repository.ExameRepository;

@Service
public class ExameService extends CrudService<Exame, Long> {
	
	@Autowired
	public ExameRepository exameRepository;
	
	@Override
	protected Exame editarEntidade(Exame recuperado, Exame entidade) {
		recuperado.setTipo(entidade.getTipo());
		recuperado.setDescricao(entidade.getDescricao());
		recuperado.setDuracao(entidade.getDuracao());
		recuperado.setDisponibilidade(entidade.getDisponibilidade());
		return recuperado;
	}

	public String tipoExame(Long id){
		return exameRepository.tipoExame(id);
	}

	public List<Exame> getTipoExame(String tipo){

		return exameRepository.exame(tipo);
	}
}

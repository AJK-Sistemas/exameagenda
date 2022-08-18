package br.com.tiacademy.exameagenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.exameagenda.core.crud.CrudService;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.domain.Paciente;

@Service
public class AgendamentoService extends CrudService<Agendamento, Long>{
    @Override
	protected Agendamento editarEntidade(Agendamento recuperado, Agendamento entidade) {
		recuperado.setDataExame(entidade.getDataExame());
		return recuperado;
	}

	@Autowired
    protected PacienteService pacienteService;

	@Override
	public Agendamento criar(Agendamento entidade){
		if (entidade.getPaciente().getId() == null){
            Paciente salvoPaciente = pacienteService.criar(entidade.getPaciente());
            entidade.setPaciente(salvoPaciente);
        }
		return repository.save(entidade);
	}
}

package br.com.tiacademy.exameagenda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.domain.Aplicador;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.domain.Paciente;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.service.AplicadorService;
import br.com.tiacademy.exameagenda.service.ExameService;
import br.com.tiacademy.exameagenda.service.PacienteService;

@Component
public class AgendamentoConverter implements CrudConverter<Agendamento, AgendamentoDTO> {
    @Autowired
    public AplicadorService aplicadorService;

    @Autowired
    public PacienteService pacienteService;

    @Autowired
    public ExameService exameService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AgendamentoDTO entidadeParaDto(Agendamento entidade) {

        return modelMapper.map(entidade, AgendamentoDTO.class);

    }

    @Override
    public Agendamento dtoParaEntidade(AgendamentoDTO dto) {
        Paciente paciente = pacienteService.porId(dto.getPaciente_id());
        Aplicador aplicador = aplicadorService.porId(dto.getAplicador_id());
        Exame exame = exameService.porId(dto.getExame_id());

        return new Agendamento(
                dto.getId(),
                dto.getDataExame(),
                dto.getDataRetirada(),
                dto.getStatus(),
                paciente,
                exame,
                aplicador);
    }

}

package br.com.tiacademy.exameagenda.converter;

import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.repository.AplicadorRepository;
import br.com.tiacademy.exameagenda.repository.ExameRepository;
import br.com.tiacademy.exameagenda.repository.PacienteRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AgendamentoConverter implements CrudConverter<Agendamento, AgendamentoDTO> {

    private final PacienteConverter pacienteConverter;
    private final ExameConverter exameConverter;
    private final AplicadorConverter aplicadorConverter;

    private final PacienteRepository pacienteRepository;
    private final ExameRepository exameRepository;
    private final AplicadorRepository aplicadorRepository;

    @Override
    public AgendamentoDTO entidadeParaDto(Agendamento entidade) {

        var dto = new AgendamentoDTO();
        dto.setId(entidade.getId());
        dto.setDataExame(entidade.getDataExame());
        dto.setHoraExame(entidade.getHoraExame());
        dto.setDataRetirada(entidade.getDataRetirada());
        dto.setStatus(entidade.getStatus());
        
        dto.setPaciente(pacienteConverter.entidadeParaDto(entidade.getPaciente()));
        dto.setExame(exameConverter.entidadeParaDto(entidade.getExame()));
        dto.setAplicador(aplicadorConverter.entidadeParaDto(entidade.getAplicador()));

        return dto;
    }

    @Override
    public Agendamento dtoParaEntidade(AgendamentoDTO dto) {

        var agendamento = new Agendamento();
        agendamento.setId(dto.getId());
        agendamento.setDataExame(dto.getDataExame());
        agendamento.setHoraExame(dto.getHoraExame());
        agendamento.setDataRetirada(dto.getDataRetirada());
        agendamento.setStatus(dto.getStatus());

        agendamento.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));
        agendamento.setExame(exameRepository.findById(dto.getExameId()).orElse(null));
        agendamento.setAplicador(aplicadorRepository.findById(dto.getAplicadorId()).orElse(null));

        return agendamento;
    }
}

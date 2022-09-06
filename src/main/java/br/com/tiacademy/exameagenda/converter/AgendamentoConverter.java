package br.com.tiacademy.exameagenda.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;
import br.com.tiacademy.exameagenda.repository.AplicadorRepository;
import br.com.tiacademy.exameagenda.repository.ExameRepository;
import br.com.tiacademy.exameagenda.repository.PacienteRepository;
@Component
public class AgendamentoConverter implements CrudConverter<Agendamento, AgendamentoDTO> {

    @Autowired
    private PacienteConverter pacienteConverter;
    
    @Autowired
    private ExameConverter exameConverter;
    
    @Autowired
    private AplicadorConverter aplicadorConverter;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ExameRepository exameRepository;
    
    @Autowired
    private AplicadorRepository aplicadorRepository;

    @Override
    public AgendamentoDTO entidadeParaDto(Agendamento entidade) {

        var dto = new AgendamentoDTO();
        dto.setId(entidade.getId());
        dto.setPacienteId(entidade.getPaciente().getId());
        dto.setExameId(entidade.getExame().getId());
        dto.setAplicadorId(entidade.getAplicador().getId());
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
        if(!Objects.isNull(dto.getId())){
        agendamento.setId(dto.getId());
        }
        agendamento.setDataExame(dto.getDataExame());
        agendamento.setHoraExame(dto.getHoraExame());
        agendamento.setDataRetirada(dto.getDataRetirada());
        agendamento.setStatus(dto.getStatus());

        if(!Objects.isNull(dto.getPacienteId())) agendamento.setPaciente(pacienteRepository.findById(dto.getPacienteId()).orElse(null));
        if(!Objects.isNull(dto.getExameId())) agendamento.setExame(exameRepository.findById(dto.getExameId()).orElse(null));
        if(!Objects.isNull(dto.getAplicadorId())) agendamento.setAplicador(aplicadorRepository.findById(dto.getAplicadorId()).orElse(null));

        return agendamento;
    }

}

package br.com.tiacademy.exameagenda.converter;

import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Agendamento;
import br.com.tiacademy.exameagenda.dto.AgendamentoDTO;

@Component
public class AgendamentoConverter implements CrudConverter<Agendamento, AgendamentoDTO> {
    @Override
    public AgendamentoDTO entidadeParaDto(Agendamento entidade) {
        return new AgendamentoDTO(
                entidade.getId(),
                entidade.getDataExame(),
                entidade.getDataRetirada(),
                entidade.getStatus(),
                entidade.getPaciente(),
                entidade.getExame(),
                entidade.getAplicador());
    }

    @Override
    public Agendamento dtoParaEntidade(AgendamentoDTO dto) {
        return new Agendamento(
                dto.getId(),
                dto.getDataExame(),
                dto.getDataRetirada(),
                dto.getStatus(),
                dto.getPaciente(),
                dto.getExame(),
                dto.getAplicador());
    }

}

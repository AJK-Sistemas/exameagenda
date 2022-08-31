package br.com.tiacademy.exameagenda.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.dto.ExameDTO;

@Component
public class ExameConverter implements CrudConverter<Exame, ExameDTO> {
    // @Autowired
    // private ModelMapper modelMapper;

    // @Override
    // public ExameDTO entidadeParaDto(Exame entidade) {
    //     return modelMapper.map(entidade, ExameDTO.class);
    // }

    // @Override
    // public Exame dtoParaEntidade(ExameDTO dto) {
    //     return modelMapper.map(dto, Exame.class);
    // }

    @Override
    public ExameDTO entidadeParaDto(Exame entidade) {
        return new ExameDTO(
                entidade.getId(),
                entidade.getTipo(),
                entidade.getDescricao(),
                entidade.getDuracao(),
                entidade.getHoraInicio(),
                entidade.getHoraFim(),
                entidade.getDisponibilidade());

    }

    @Override
    public Exame dtoParaEntidade(ExameDTO dto) {
        return new Exame(
                dto.getId(),
                dto.getTipo(),
                dto.getDescricao(),
                dto.getDuracao(),
                dto.getHoraInicio(),
                dto.getHoraFim(),
                dto.getDisponibilidade());
    }
}

package br.com.tiacademy.exameagenda.converter;

import org.springframework.stereotype.Component;

import br.com.tiacademy.exameagenda.core.crud.CrudConverter;
import br.com.tiacademy.exameagenda.domain.Exame;
import br.com.tiacademy.exameagenda.dto.ExameDTO;

@Component
public class ExameConverter implements CrudConverter<Exame, ExameDTO>{
    @Override
    public ExameDTO entidadeParaDto(Exame entidade) {
        return new ExameDTO(
                entidade.getId(),
                entidade.getTipo(),
                entidade.getDescricao(),
                entidade.getDuracao(),
                entidade.getDisponibilidade(),
                entidade.getValor());
    }

    @Override
    public Exame dtoParaEntidade(ExameDTO dto) {
        return new Exame(
                dto.getId(),
                dto.getTipo(),
                dto.getDescricao(),
                dto.getDuracao(),
                dto.getDisponibilidade(),
                dto.getValor());
    }
}

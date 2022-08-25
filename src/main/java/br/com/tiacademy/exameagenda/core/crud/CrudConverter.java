package br.com.tiacademy.exameagenda.core.crud;

public interface CrudConverter<T, D> {

    D entidadeParaDto(T entidade);

    T dtoParaEntidade(D dto);
}

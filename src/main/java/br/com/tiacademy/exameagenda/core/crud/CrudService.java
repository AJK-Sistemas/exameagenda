package br.com.tiacademy.exameagenda.core.crud;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CrudService<T, ID> {

	@Autowired
	protected CrudRepository<T, ID> repository;

	public Page<T> pagination(Pageable pageable) {

		return repository.findAll(pageable);
	}

	public List<T> listar() {
		return repository.findAll();
	}

	public T porId(ID id) {

		var entidade = repository.findById(id).orElse(null);
		if (Objects.isNull(entidade)) {
			throw new RuntimeException("Objeto não foi encontrado!");
		}

		return entidade;
	}

	public T criar(T entidade) {

		return repository.save(entidade);
	}

	public T editar(ID id, T entidade) {

		T recuperado = porId(id);

		if (Objects.isNull(recuperado)) {
			throw new RuntimeException("Objeto não foi encontrado!");
		}

		T entidadeSalvar = editarEntidade(recuperado, entidade);

		return repository.save(entidadeSalvar);
	}

	public void excluir(ID id) {

		T recuperado = porId(id);
		if (Objects.isNull(recuperado)) {
			throw new RuntimeException("Objeto não foi encontrado!");
		}
		repository.deleteById(id);
	}

	protected abstract T editarEntidade(T recuperado, T entidade);
}

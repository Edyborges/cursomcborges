package com.borges.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Categoria;
import com.borges.cursomc.dto.CategoriaDto;
import com.borges.cursomc.repositories.CategoriaRepository;
import com.borges.cursomc.services.exceptions.DataIntegrityException;
import com.borges.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer id) {

		Optional<Categoria> object = repository.findById(id);

		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria object) {
		object.setId(null);
		return repository.save(object);
	}

	public Categoria update(Categoria object) {
		Categoria newObject = find(object.getId());
		updateData(newObject, object);
		return repository.save(newObject);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir uma categoria que contém um produto relacionado a ela.");
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDto ObjectDto) {
		return new Categoria(ObjectDto.getId(), ObjectDto.getNome());
	}
	
	private void updateData(Categoria newObject, Categoria object) {
		newObject.setNome(object.getNome());
		
	}
}

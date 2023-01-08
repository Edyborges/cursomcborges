package com.borges.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Cliente;
import com.borges.cursomc.dto.ClienteDto;
import com.borges.cursomc.repositories.ClienteRepository;
import com.borges.cursomc.services.exceptions.DataIntegrityException;
import com.borges.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {

		Optional<Cliente> object = repository.findById(id);

		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! " + id + ", Tipo:" + Cliente.class.getName()));

	}
	
	public Cliente update(Cliente object) {
		Cliente newObject = find(object.getId());
		updateData(newObject, object);
		return repository.save(newObject);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir um cliente que contém outras classes relacionadas a ela.");
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDto ObjectDto) {
		return new Cliente(ObjectDto.getId(), ObjectDto.getNome(), ObjectDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObject, Cliente object) {
		newObject.setNome(object.getNome());
		newObject.setEmail(object.getEmail());
	}
}

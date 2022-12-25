package com.borges.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Cliente;
import com.borges.cursomc.repositories.ClienteRepository;
import com.borges.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {

		Optional<Cliente> objeto = repository.findById(id);

		return objeto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! " + id + ", Tipo:" + Cliente.class.getName()));

	}
}

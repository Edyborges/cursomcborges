package com.borges.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Categoria;
import com.borges.cursomc.repositories.CategoriaRepository;
import com.borges.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer id) {
	   
	   Optional<Categoria> objeto = repository.findById(id);
	   
	   return objeto.orElseThrow(() -> new ObjectNotFoundException(
			   "Objeto não encontrado ! Id: " + id + ", Tipo: " + Categoria.class.getName()));
   }
}

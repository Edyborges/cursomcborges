package com.borges.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Categoria;
import com.borges.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer id) {
	   
	   Optional<Categoria> obj = repository.findById(id);
	   return obj.orElse(null);
   }
}

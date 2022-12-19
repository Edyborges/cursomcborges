package com.borges.cursomc.resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borges.cursomc.domain.Categoria;
import com.borges.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategorigaResources {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria objeto = service.buscar(id);

		return ResponseEntity.ok().body(objeto);
	}
}

package com.borges.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borges.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categoria")
public class CategorigaResources {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat = new Categoria(1, "Informática");
		Categoria cat2 =  new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat);
		lista.add(cat2);
		return lista;
	} 
}

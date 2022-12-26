package com.borges.cursomc.resources;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.borges.cursomc.domain.Categoria;
import com.borges.cursomc.dto.CategoriaDto;
import com.borges.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategorigaResources {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria objeto = service.find(id);
		return ResponseEntity.ok().body(objeto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria objeto){
		objeto = service.insert(objeto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(objeto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria objeto, @PathVariable Integer id){
		
		objeto.setId(id);
		objeto = service.update(objeto);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDto>> findAll() {
		List<Categoria> list = service.findAll();
		List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}

package com.borges.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria")
public class CategorigaResources {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "";
	} 
}

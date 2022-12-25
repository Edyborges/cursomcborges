package com.borges.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Pedido;
import com.borges.cursomc.repositories.PedidoRepository;
import com.borges.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido find(Integer id) {
	   
	   Optional<Pedido> objeto = repository.findById(id);
	   
	   return objeto.orElseThrow(() -> new ObjectNotFoundException(
			   "Objeto não encontrado ! Id: " + id + ", Tipo: " + Pedido.class.getName()));
   }
}

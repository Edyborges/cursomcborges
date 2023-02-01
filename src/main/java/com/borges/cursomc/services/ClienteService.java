package com.borges.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.borges.cursomc.domain.Cidade;
import com.borges.cursomc.domain.Cliente;
import com.borges.cursomc.domain.Endereco;
import com.borges.cursomc.domain.enums.TipoCliente;
import com.borges.cursomc.dto.ClienteDto;
import com.borges.cursomc.dto.ClienteNewDto;
import com.borges.cursomc.repositories.CidadeRepository;
import com.borges.cursomc.repositories.ClienteRepository;
import com.borges.cursomc.repositories.EnderecoRepository;
import com.borges.cursomc.services.exceptions.DataIntegrityException;
import com.borges.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cliente find(Integer id) {

		Optional<Cliente> object = repository.findById(id);

		return object.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ! " + id + ", Tipo:" + Cliente.class.getName()));

	}
	
    @Transactional
	public Cliente insert(Cliente object) {
		
		object.setId(null);
	
		return repository.save(object);
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
					"Não é possível excluir um cliente com pedidos em aberto.");
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
	
	public Cliente fromDto(ClienteNewDto ObjectDto) {
		Cliente cli = new Cliente(null, ObjectDto.getNome(), ObjectDto.getEmail(), ObjectDto.getCpfOuCnpj(), TipoCliente.toEnum(ObjectDto.getTipo()));
		Cidade cid = cidadeRepository.getOne(ObjectDto.getCidadeId());
		Endereco end = new Endereco(null, ObjectDto.getLogradouro(), ObjectDto.getNumero(), ObjectDto.getBairro(), ObjectDto.getComplemento(), ObjectDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(ObjectDto.getTelefone1());
				
		if(ObjectDto.getTelefone2()!=null) {
			cli.getTelefones().add(ObjectDto.getTelefone2());
		}
		
		if(ObjectDto.getTelefone3()!=null) {
			cli.getTelefones().add(ObjectDto.getTelefone3());
		}
		
		return cli;
		
	}
	
	private void updateData(Cliente newObject, Cliente object) {
		newObject.setNome(object.getNome());
		newObject.setEmail(object.getEmail());
	}
}

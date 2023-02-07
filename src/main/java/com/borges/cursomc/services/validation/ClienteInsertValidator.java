package com.borges.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.borges.cursomc.domain.Cliente;
import com.borges.cursomc.domain.enums.TipoCliente;
import com.borges.cursomc.dto.ClienteNewDto;
import com.borges.cursomc.repositories.ClienteRepository;
import com.borges.cursomc.resources.exception.FieldMessage;
import com.borges.cursomc.services.validation.utils.BR;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDto objectDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objectDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objectDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if (objectDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objectDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objectDto.getEmail());
		
		if(aux !=null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

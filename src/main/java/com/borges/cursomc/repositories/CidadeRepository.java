package com.borges.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.borges.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}

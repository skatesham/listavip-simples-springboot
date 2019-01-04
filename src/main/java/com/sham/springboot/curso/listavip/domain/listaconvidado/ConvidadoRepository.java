package com.sham.springboot.curso.listavip.domain.listaconvidado;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado, Long> {

	public Optional<Convidado> findByEmail(String email);
	
}

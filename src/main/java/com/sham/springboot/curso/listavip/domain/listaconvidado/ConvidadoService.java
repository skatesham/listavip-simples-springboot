package com.sham.springboot.curso.listavip.domain.listaconvidado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvidadoService {

	@Autowired
	ConvidadoRepository convidadoRepository;
	
	public Iterable<Convidado> obterTodos(){
		return convidadoRepository.findAll();
	}
	
	public Convidado buscarPorId(Long id) {
		return convidadoRepository.findById(id).get();
	}
	
	public void salvar(Convidado convidado) {
		convidadoRepository.save(convidado);
	}
	
	public void deletar(Convidado convidado) {
		convidadoRepository.delete(convidado);
	}
	
	public void deletarPorId(Long id) {
		Convidado convidado = this.buscarPorId(id);
		this.deletar(convidado);
		
	}
	
}

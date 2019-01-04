package com.sham.springboot.curso.listavip.interfaces;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sham.springboot.curso.listavip.domain.listaconvidado.Convidado;
import com.sham.springboot.curso.listavip.domain.listaconvidado.ConvidadoRepository;

@Controller
public class ConvidadoController {

	@Autowired
	ConvidadoRepository convidadoRepository;

	@RequestMapping("listaconvidados")
	public String listaconvidados(Model model) {
		
		Iterable<Convidado> convidados = convidadoRepository.findAll();
		
		if(!convidados.iterator().hasNext()) {
			// If lista convidados vazias, criar convidados e buscar novamente
			Convidado[] convidado = new Convidado[3];
			convidado[0] = new Convidado("Administrador","admin@listavip.com","(12) 98745-4321");
			convidado[1] = new Convidado("Sham Vinicius Fiorin","sham.vinicius@gmail.com","(12) 99665-7941");
			convidado[2] = new Convidado("James Brown","james@listavip.com","(12) 98745-9874");
			Arrays.asList(convidado).stream().forEach(c -> convidadoRepository.save(c));
			convidados = convidadoRepository.findAll();
		}
		
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}

}

package com.sham.springboot.curso.listavip.interfaces;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sham.springboot.curso.listavip.domain.listaconvidado.Convidado;
import com.sham.springboot.curso.listavip.domain.listaconvidado.ConvidadoService;
import com.sham.springboot.curso.listavip.domain.listaconvidado.EmailService;

@Controller
public class ConvidadoController {

	@Autowired
	ConvidadoService convidadoService;

	@RequestMapping("listaconvidados")
	public String listaconvidados(Model model) {

		Iterable<Convidado> convidados = convidadoService.obterTodos();

		if (!convidados.iterator().hasNext()) {
			// If lista convidados vazias, criar convidados e buscar novamente
			Convidado[] convidado = new Convidado[3];
			convidado[0] = new Convidado("Administrador", "admin@listavip.com", "(12) 98745-4321");
			convidado[1] = new Convidado("Sham Vinicius Fiorin", "sham.vinicius@gmail.com", "(12) 99665-7941");
			convidado[2] = new Convidado("James Brown", "james@listavip.com", "(12) 98745-9874");
			Arrays.asList(convidado).stream().forEach(c -> convidadoService.salvar(c));
			convidados = convidadoService.obterTodos();
		}

		model.addAttribute("convidados", convidados);

		return "listaconvidados";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("telefone") String telefone,
			@RequestParam("email") String email, Model model) {

		// Criar novo Usuario
		Convidado convidado = new Convidado(nome, email, telefone);
		convidadoService.salvar(convidado);

		// Enviar Email para convidado
		new EmailService().enviar(nome, email);

		return "redirect:listaconvidados";
	}

	@RequestMapping(value = "deletar")
	public String deletar(@RequestParam("id") Long id, Model model) {

		convidadoService.deletarPorId(id);

		return "redirect:listaconvidados";

	}

}

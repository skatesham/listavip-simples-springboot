package com.sham.springboot.curso.listavip.interfaces;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControler {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@ExceptionHandler(NotFoundException.class)
	public String error() {
		return "error";
	}
	
}

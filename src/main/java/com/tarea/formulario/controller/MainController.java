package com.tarea.formulario.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		/* No es necesario poner la extension .html a index, ya que el metodo
		 * templateResolver.setSuffix(".html") sirve para asumir dicha extension.
		 * Como solo vamos a redirigir a la pagina index.html no es necesario un 
		 * objeto ModelAndView, al devolver un tipo de dato String, Spring automaticamente
		 * asume que es una vista la que se quiere devolver.
		 */
		return "index";
	}
	
	@RequestMapping("/resultado")
	public ModelAndView parametros1(HttpServletRequest request) throws ParseException {
		ModelAndView mav = new ModelAndView();
		List<String> lista = new ArrayList<>();
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String fecha = request.getParameter("fecha");
		Date fechan = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		Date minDate = new SimpleDateFormat("yyyy-MM-dd").parse("2003-01-01");
		//Date minDate = new Date(2003, 01, 01);
		String lugar = request.getParameter("lugar");
		String instituto = request.getParameter("instituto");
		String fijo = request.getParameter("fijo");
		String movil = request.getParameter("movil");
		
		
		if(nombre.length()<1 || nombre.length()>25) {
			lista.add("El campo Nombre debe llevar entre 1 y 25 caracteres.");
		}
		if(apellido.length()<1 || apellido.length()>25) {
			lista.add("El campo Apellido debe llevar entre 1 y 25 caracteres.");
		}
		if(fechan.compareTo(minDate) < 0) {
			lista.add("La fecha de Nacimiento no puede ser menor al 1 de Enero del 2003.");
		}
		if(lugar.length()<1 || lugar.length()>25) {
			lista.add("El campo Lugar de Nacimiento debe llevar entre 1 y 25 caracteres.");
		}
		if(instituto.length()<1 || instituto.length()>100) {
			lista.add("El campo nombre debe llevar entre 1 y 100 caracteres.");
		}
		if(fijo.length()!=8) {
			lista.add("El campo Telefono Fijo debe llevar 8 caracteres.");
		}
		if(movil.length()!=8) {
			lista.add("El campo Telefono Movil debe llevar 8 caracteres.");
		}
		
		
		if(!lista.isEmpty()) {
			mav.addObject("lista", lista);
			mav.setViewName("resultado");
			return mav;
		}
		mav.setViewName("resultado2");
		return mav;
		
		
	}

}
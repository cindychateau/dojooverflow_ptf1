package com.codingdojo.cynthia.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.cynthia.modelos.Etiqueta;
import com.codingdojo.cynthia.modelos.Pregunta;
import com.codingdojo.cynthia.modelos.Respuesta;
import com.codingdojo.cynthia.servicios.Servicios;

@Controller
public class Controladores {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/")
	public String index(Model model) {
		//List<Pregunta> preguntas = servicio.todasPreguntas();
		//model.addAttribute("preguntas", preguntas);
		model.addAttribute("preguntas", servicio.todasPreguntas());
		return "index.jsp";
	}
	
	@GetMapping("/nueva")
	public String nueva(@ModelAttribute("pregunta") Pregunta pregunta) {
		return "nueva.jsp";
	}
	
	@PostMapping("/crear")
	public String crear(@Valid @ModelAttribute("pregunta") Pregunta pregunta,
						BindingResult result,
						@RequestParam("textoEtiquetas") String textoEtiquetas) {
		
		if(result.hasErrors()) {
			return "nueva.jsp";
		} else {
			//textoEtiquetas = "java,listado,pregunta"
			//listaEtiquetas = {"java", "listado", "pregunta"}
			//trim() -> quita los espacio alrededor
			//split() -> divide el texto en base a un caracter
			String[] listaEtiquetas = textoEtiquetas.trim().split(",");
			List<Etiqueta> etiquetas = new ArrayList<>();
			
			/*
			 * listaEtiquetas = {"java", "listado", "pregunta"}
			 * 
			 * tema = "java"
			 * et = null //porque no exite esa etiqueta
			 * etiquetas = {Obj(java)}
			 * 
			 * tema = "listado"
			 * et = Obj("listado")
			 * etiquetas = {Obj(java), Obj("listado")}
			 */
			for(String tema:listaEtiquetas) {
				//Buscar si existe ese tema entre las etiquetas
				Etiqueta et = servicio.encuentraEtiqueta(tema);
				
				if(et == null) {
					//No existe la etiqueta y la debemos crear
					Etiqueta nuevaEtiqueta = new Etiqueta();
					nuevaEtiqueta.setTema(tema); //java
					servicio.guardarEtiqueta(nuevaEtiqueta);
					etiquetas.add(nuevaEtiqueta);
				} else {
					etiquetas.add(et);
				}
			}
			
			pregunta.setEtiquetas(etiquetas);
			servicio.guardarPregunta(pregunta);
			return "redirect:/";
			
		}
		
	}
	
	@GetMapping("/pregunta/{id}")
	public String pregunta(@PathVariable("id") Long id,
						   Model model,
						   @ModelAttribute("respuesta") Respuesta respuesta) {
		
		Pregunta pregunta = servicio.encuentraPregunta(id);
		//model.addAttribute("pregunta", pregunta);
		model.addAttribute("pregunta", pregunta);
		
		return "pregunta.jsp";
	}
	
	@PostMapping("/respuesta")
	public String respuesta(@Valid @ModelAttribute("respuesta") Respuesta respuesta,
							BindingResult result) {
		if(result.hasErrors()) {
			return "pregunta.jsp"; 
		} else {
			servicio.guardarRespuesta(respuesta);
			return "redirect:/pregunta/"+respuesta.getPregunta().getId();
		}
	}
	
}

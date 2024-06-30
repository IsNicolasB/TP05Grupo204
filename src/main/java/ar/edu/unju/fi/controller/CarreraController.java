package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {
	@Autowired
	Carrera nuevaCarrera;
	
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera", nuevaCarrera);	
		modelView.addObject("flag", false);
		return modelView;
	}
	
	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraParaGuardar, BindingResult resultado) {
		ModelAndView modelView = new ModelAndView();
		
		if (resultado.hasErrors()) {
			modelView.setViewName("formCarrera");
			modelView.addObject("flag", false);
		}
		else {
			carreraService.guardarCarrera(carreraParaGuardar);
			modelView.setViewName("listaDeCarreras");
			modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());	
		}
		return modelView;
	}
	
	@GetMapping("/listadoCarreras")
	public ModelAndView getFormListaCarrera() {
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		return modelView;	
	}
	

	@GetMapping("/borrarCarrera/{codigo}")
	public ModelAndView deleteCarreraDelListado(@PathVariable(name="codigo") String codigo) {

		carreraService.borrarCarrera(codigo);
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());	
		
		return modelView;		
		}
	
	  @GetMapping("/modificarCarrera/{codigo}")
	    public ModelAndView getFormModificarCarrera(@PathVariable(name="codigo") Integer codigo) {
	        Carrera carrera = carreraService.buscarCarrera(codigo);
	        ModelAndView modelView = new ModelAndView("formCarrera");
	        modelView.addObject("nuevaCarrera", carrera);
	        modelView.addObject("flag", true);
	        return modelView;
	    }

	  @PostMapping("/modificarCarrera")
	    public ModelAndView modificarCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraModificada, BindingResult resultado) {
	    	
		  	ModelAndView modelView = new ModelAndView();
			
			if (resultado.hasErrors()) {
				modelView.setViewName("formCarrera");
				modelView.addObject("flag", true);
			}
			else {
				carreraService.modificarCarrera(carreraModificada);
				modelView.setViewName("listaDeCarreras");
				modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());	
			}

	        return modelView;
	    }
}
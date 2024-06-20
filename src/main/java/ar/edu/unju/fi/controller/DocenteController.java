package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;

@Controller
public class DocenteController {
	@Autowired
	Docente nuevoDocente;
	
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/formularioDocente")
	public ModelAndView getFormDocente() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("formDocente");
		modelView.addObject("nuevoDocente", nuevoDocente);	
		modelView.addObject("flag", false);
		return modelView;
	}
	
	@PostMapping("/guardarDocente")
	public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") Docente docente) {

		//ListadoDocentes.agregarDocente(docente);
		docenteService.guardarDocente(docente);
		
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		//modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());	
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
		
		return modelView;		
	}
	
	@GetMapping("/listadoDocentes")
	public ModelAndView getFormListaDocente() {
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());	
		
		return modelView;	
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView deleteDocenteDelListado(@PathVariable(name="legajo") String legajo) {
		//borrar
		//ListadoDocentes.eliminarDocente(legajo);
		docenteService.borrarDocente(legajo);
		ModelAndView modelView = new ModelAndView("listaDeDocentes");
		modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());	
		
		return modelView;		
		}
	
	@GetMapping("/modificarDocente/{legajo}")
    public ModelAndView getFormModificarDocente(@PathVariable(name="legajo") String legajo) {
		Docente docente = docenteService.buscarDocente(legajo);
        //ModelAndView modelView = new ModelAndView("modificarCarrera");
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", docente);
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarDocente")
    public ModelAndView modificarDocente(@ModelAttribute("nuevoDocente") Docente docenteModificado) {
        docenteService.modificarDocente(docenteModificado);
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        return modelView;
    }
}

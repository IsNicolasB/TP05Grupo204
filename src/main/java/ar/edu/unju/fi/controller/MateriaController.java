package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.MateriaService;

@Controller
public class MateriaController {
	
	@Autowired
	Materia nuevaMateria;
	
	@Autowired
	MateriaService materiaService;
	
	@GetMapping("/formularioMaterias")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", nuevaMateria);	
		//modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		//modelView.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		modelView.addObject("flag", false);
		modelView.addObject("band", false);
		return modelView;
	}
	
	@GetMapping("/listadoMaterias")
	public ModelAndView getFormListaMateria() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());	
		return modelView;	
	}
	
	@PostMapping("/guardarMateria")
	public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
		//materiaParaGuardar.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaParaGuardar.getDocente().getLegajo()));
		//materiaParaGuardar.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaParaGuardar.getCarrera().getCodigo()));
		//ListadoMaterias.agregarMateria(materiaParaGuardar);
		materiaService.guardarMateria(materiaParaGuardar);
		
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		//modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());	
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());	
		
		return modelView;		
	}
	
	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") String codigo) {
		//borrar
		//ListadoMaterias.eliminarMateria(codigo);
		materiaService.borrarMateria(codigo);
		//Mostrar listado
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());	
		
		return modelView;		
		}
	
	@GetMapping("/modificarMateria/{codigo}")
    public ModelAndView getFormModificarMateria(@PathVariable(name="codigo") String codigo) {
        //Materia materia = ListadoMaterias.buscarMateriaPorCodigo(codigo);
        Materia materia = materiaService.buscarMateria(codigo);
		System.out.println(materia.getCodigo());
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materia);
        //modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		//modelView.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView modificarMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
		//materiaModificada.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaModificada.getDocente().getLegajo()));
		//materiaModificada.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaModificada.getCarrera().getCodigo()));
        //ListadoMaterias.modificarMateria(materiaModificada);
        materiaService.modificarMateria(materiaModificada);
    	ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        return modelView;
    }
    
}
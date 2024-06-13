package ar.edu.unju.fi.controller;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoCarreras;
import ar.edu.unju.fi.collections.ListadoDocentes;
import ar.edu.unju.fi.collections.ListadoMaterias;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriaController {
	@Autowired
	Materia nuevaMateria;
	
	@GetMapping("/formularioMaterias")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria", nuevaMateria);	
		modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		modelView.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
		modelView.addObject("flag", false);
		return modelView;
	}
	
	@GetMapping("/listadoMaterias")
	public ModelAndView getFormListaMateria() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());	
		return modelView;	
	}
	
	@PostMapping("/guardarMateria")
	public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar) {
		materiaParaGuardar.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaParaGuardar.getDocente().getLegajo()));
		materiaParaGuardar.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaParaGuardar.getCarrera().getCodigo()));
		ListadoMaterias.agregarMateria(materiaParaGuardar);
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());	
		
		return modelView;		
	}
	
	@GetMapping("/borrarMateria/{codigo}")
	public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") String codigo) {
		//borrar
		ListadoMaterias.eliminarMateria(codigo);
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());	
		
		return modelView;		
		}
	
	@GetMapping("/modificarMateria/{codigo}")
    public ModelAndView getFormModificarMateria(@PathVariable(name="codigo") String codigo) {
        Materia materia = ListadoMaterias.buscarMateriaPorCodigo(codigo);
        System.out.println(materia.getCodigo());
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materia);
        modelView.addObject("listadoDocentes", ListadoDocentes.listarDocentes());
		modelView.addObject("listadoCarreras", ListadoCarreras.listarCarreras());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView modificarMateria(@ModelAttribute("nuevaMateria") Materia materiaModificada) {
		materiaModificada.setDocente(ListadoDocentes.buscarDocentePorLegajo(materiaModificada.getDocente().getLegajo()));
		materiaModificada.setCarrera(ListadoCarreras.buscarCarreraPorCodigo(materiaModificada.getCarrera().getCodigo()));
        ListadoMaterias.modificarMateria(materiaModificada);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", ListadoMaterias.listarMaterias());
        return modelView;
    }
}
*/
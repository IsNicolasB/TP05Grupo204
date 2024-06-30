package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class MateriaController {
    
    @Autowired
    Materia nuevaMateriaDTO;
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    DocenteService docenteService;
    
    @GetMapping("/formularioMaterias")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateriaDTO);
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        modelView.addObject("flag", false);
        modelView.addObject("band", false);
        return modelView;
    }
    
    @GetMapping("/listadoMaterias")
    public ModelAndView getFormListaMateria() {
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        return modelView;    
    }
    
    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaParaGuardar, BindingResult resultado) {
        ModelAndView modelView = new ModelAndView();
        if (resultado.hasErrors() ) {
        	modelView.setViewName("formMateria");
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        } else {
        	modelView.setViewName("listaDeMaterias");
        	materiaService.guardarMateria(materiaParaGuardar);
            modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
            
        }
        return modelView;
    }
    
    @GetMapping("/borrarMateria/{codigo}")
    public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") String codigo) {
        materiaService.borrarMateria(codigo);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        return modelView;
    }
    
    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView getFormModificarMateria(@PathVariable(name="codigo") String codigo) {
        Materia materia = materiaService.buscarMateria(codigo);
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materia);
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView modificarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaModificada, BindingResult resultado) {
        
    	
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        if (resultado.hasErrors()) {
        	modelView.setViewName("formMateria");
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
            modelView.addObject("flag", true);
        } else {
        	materiaService.modificarMateria(materiaModificada);
        	modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        }
        
        return modelView;
    }
}

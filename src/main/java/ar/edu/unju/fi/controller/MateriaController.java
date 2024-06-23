package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.MateriaService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class MateriaController {
    
    @Autowired
    MateriaDTO nuevaMateriaDTO;
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    DocenteService docenteService;
    
    @Autowired
    CarreraService carreraService;
    
    @GetMapping("/formularioMaterias")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateriaDTO);
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
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
    public ModelAndView saveMateria(@ModelAttribute("nuevaMateria") MateriaDTO materiaParaGuardar) {
        materiaService.guardarMateria(materiaParaGuardar);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
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
        MateriaDTO materia = materiaService.buscarMateria(codigo);
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materia);
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView modificarMateria(@ModelAttribute("nuevaMateria") MateriaDTO materiaModificada) {
        materiaService.modificarMateria(materiaModificada);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        return modelView;
    }
}

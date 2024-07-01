package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class MateriaController {
    
    @Autowired
    Materia nuevaMateriaDTO;
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    DocenteService docenteService;
    
    @Autowired
    AlumnoService alumnoService;
    
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
    
    @GetMapping("/inscripcionMateria")
    public ModelAndView getInscripcionMateria() {
        ModelAndView modelView = new ModelAndView("inscripcionMateria");
        modelView.addObject("listaDeMaterias", materiaService.mostrarMaterias());
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
        modelView.addObject("alumno", new Alumno()); 
        return modelView;
    }
    
    @PostMapping("/guardarInscripcion")
    public ModelAndView inscribirMateria(@ModelAttribute("alumno") Alumno alumno, BindingResult result) {
        ModelAndView modelView = new ModelAndView();
        if (result.hasErrors()) {
        	modelView.setViewName("inscripcionMateria");
            modelView.addObject("listaDeMaterias", materiaService.mostrarMaterias());
            modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
        } else {
        	modelView.setViewName("inscripcionMateria");
        	String alumnoId = alumno.getLu();
            String materiaCodigo = alumno.getMaterias().get(0).getCodigo();
            
          alumnoService.inscribirMateria(alumnoId, materiaCodigo);
        	
        }
        
        return modelView;
    }
    
	  @GetMapping("/alumnosPorMateria/{codigo}")
		public ModelAndView getFormAlumnosPorMateria(@PathVariable(name="codigo") String codigo) {
			ModelAndView modelView = new ModelAndView("alumnoXmateria");
			modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
			modelView.addObject("materia" , materiaService.buscarMateria(codigo));
			
			return modelView;	
		}
    
    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaParaGuardar, BindingResult resultado) {
        ModelAndView modelView = new ModelAndView();

        if (resultado.hasErrors()) {
            modelView.setViewName("formMateria");
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        } else {
            try {
            	materiaService.guardarMateria(materiaParaGuardar);
            } catch(Exception e) {
            	modelView.addObject("errors", true);
            	modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD" + e.getMessage());
            	System.out.println(e.getMessage());
            }
            modelView.setViewName("listaDeMaterias");
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

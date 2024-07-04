package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class MateriaController {
    
    @Autowired
    Materia nuevaMateria;
    
    @Autowired
    MateriaService materiaService;
    
    @Autowired
    DocenteService docenteService;
    
    @Autowired
    CarreraService carreraService;
    
    @Autowired
    AlumnoService alumnoService;
    
    @GetMapping("/formularioMaterias")
    public ModelAndView getFormMateria() {
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", nuevaMateria);
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        modelView.addObject("flag", false);
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
    public ModelAndView inscribirMateria(@RequestParam("alumnoId") String alumnoId, @RequestParam("materiaId") Integer materiaId) {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("inscripcionMateria");
        modelView.addObject("listaDeMaterias", materiaService.mostrarMaterias());
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());

        Alumno alumno = alumnoService.buscarAlumno(alumnoId);
        Materia materia = materiaService.buscarMateria(materiaId);

        if (alumno == null || materia == null) {
            modelView.addObject("Mensaje", "Error: Alumno o Materia no encontrados.");
            modelView.addObject("Band", 1);
        } else if (alumno.getMaterias().contains(materia)) {
            modelView.addObject("Mensaje", "El alumno ya está inscripto en esta materia");
            modelView.addObject("Band", 2);
        } else {
            modelView.addObject("Mensaje", "La inscripción se realizó correctamente");
            modelView.addObject("Band", 3);
            alumno.getMaterias().add(materia);
            alumnoService.inscribirMateria(alumno);
        }

        return modelView;
    }

	  @GetMapping("/alumnosPorMateria/{codigo}")
		public ModelAndView getFormAlumnosPorMateria(@PathVariable(name="codigo") Integer codigo) {
			ModelAndView modelView = new ModelAndView("alumnoXmateria");
			modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
	        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());

			modelView.addObject("materia" , materiaService.buscarMateria(codigo));
			
			return modelView;	
		}
    
    @PostMapping("/guardarMateria")
    public ModelAndView saveMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaParaGuardar, BindingResult resultado) {
        ModelAndView modelView = new ModelAndView();

        if (resultado.hasErrors()) {
            modelView.setViewName("formMateria");
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
            modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
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
    public ModelAndView deleteMateriaDelListado(@PathVariable(name="codigo") Integer codigo) {
        materiaService.borrarMateria(codigo);
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        return modelView;
    }
    
    @GetMapping("/modificarMateria/{codigo}")
    public ModelAndView getFormModificarMateria(@PathVariable(name="codigo") Integer codigo) {
        Materia materia = materiaService.buscarMateria(codigo);
        if (materia.getCarrera() != null) materiaService.borrarRelaciones(materia);
        ModelAndView modelView = new ModelAndView("formMateria");
        modelView.addObject("nuevaMateria", materia);
        modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
        modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarMateria")
    public ModelAndView modificarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materiaModificada, BindingResult resultado) {
        
    	
        ModelAndView modelView = new ModelAndView("listaDeMaterias");
        if (resultado.hasErrors()) {
        	if (materiaModificada.getCarrera() != null) materiaService.borrarRelaciones(materiaModificada);
        	
        	modelView.setViewName("formMateria");
            modelView.addObject("listadoDocentes", docenteService.mostrarDocentes());
            modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
            modelView.addObject("flag", true);
        } else {
        	if (materiaModificada.getCarrera() != null) materiaModificada.getCarrera().getMaterias().add(materiaModificada);
        	
        	try {
            	materiaService.guardarMateria(materiaModificada);
            } catch(Exception e) {
            	modelView.addObject("errors", true);
            	modelView.addObject("cargaMateriaErrorMessage", "Error al cargar en la BD" + e.getMessage());
            	System.out.println(e.getMessage());
            }
        	
        	modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
        }
        
        return modelView;
    }
}

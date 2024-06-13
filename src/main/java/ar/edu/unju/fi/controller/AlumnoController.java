package ar.edu.unju.fi.controller;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.ListadoAlumnos;
import ar.edu.unju.fi.collections.ListadoDocentes;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Docente;

@Controller
public class AlumnoController {

	@Autowired
	Alumno nuevoAlumno = new Alumno();
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", nuevoAlumno);	
		modelView.addObject("flag", false);
		return modelView;
	}
	
	@GetMapping("/listadoAlumnos")
	public ModelAndView getFormListaAlumno() {
		
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());	
		
		return modelView;	
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoParaGuardar) {
					
		ListadoAlumnos.agregarAlumno(alumnoParaGuardar);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());	
		
		return modelView;		
	}
	
	@GetMapping("/borrarAlumno/{lu}")
	public ModelAndView deleteAlumnoDelListado(@PathVariable(name="lu") String lu) {

		ListadoAlumnos.eliminarAlumno(lu);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());	
		
		return modelView;		
		}
	
	@GetMapping("/modificarAlumno/{lu}")
    public ModelAndView getFormModificarAlumno(@PathVariable(name="lu") String lu) {
		Alumno alumno = ListadoAlumnos.buscarAlumnoPorLu(lu);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", alumno);
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarAlumno")
    public ModelAndView modificarAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoModificado) {
        ListadoAlumnos.modificarAlumno(alumnoModificado);
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumnos", ListadoAlumnos.listarAlumnos());
        return modelView;
    }
    
}*/

package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	private boolean guardado=true;
	@Autowired
	Alumno nuevoAlumno;
	
	@Autowired
	CarreraService carreraService;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", nuevoAlumno);	
		modelView.addObject("carreras",carreraService.mostrarCarreras());
		modelView.addObject("flag", false);
		return modelView;
	}
	
	@GetMapping("/listadoAlumnos")
	public ModelAndView getFormListaAlumno() {
		
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());	
		
		return modelView;	
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoParaGuardar, BindingResult r) {
		
		ModelAndView modelView;
		if(r.hasErrors()) {
			modelView=new ModelAndView("formAlumno");
			modelView.addObject("carreras",carreraService.mostrarCarreras());
			modelView.addObject("flag", false);
		}else {
			guardado=alumnoService.guardarAlumno(alumnoParaGuardar);
			modelView=new ModelAndView("listaDeAlumnos");
			modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());	
			modelView.addObject("saved", guardado);
			guardado=true;
		}
		return modelView;		
	}
	
	@GetMapping("/borrarAlumno/{lu}")
	public ModelAndView deleteAlumnoDelListado(@PathVariable(name="lu") String lu) {

		alumnoService.borrarAlumno(lu);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());	
		
		return modelView;		
		}
	
	@GetMapping("/modificarAlumno/{lu}")
    public ModelAndView getFormModificarAlumno(@PathVariable(name="lu") String lu) {
		Alumno alumno = alumnoService.buscarAlumno(lu);
		if(alumno.getCarrera() != null) alumnoService.borrarRelaciones(alumno);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", alumno);
        modelView.addObject("carreras",carreraService.mostrarCarreras());
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarAlumno")
    public ModelAndView modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoModificado, BindingResult r) {
    	ModelAndView modelView;
    	if(r.hasErrors()) {
    		//if(alumnoModificado.getCarrera() != null)  alumnoService.borrarRelaciones(alumnoModificado);
    		modelView = new ModelAndView("formAlumno");
    		modelView.addObject("carreras",carreraService.mostrarCarreras());
    		modelView.addObject("flag", true);
    	}else {
    		if(alumnoModificado.getCarrera() != null) alumnoModificado.getCarrera().getAlumnos().add(alumnoModificado);
    		alumnoService.modificarAlumno(alumnoModificado);
            modelView= new ModelAndView("listaDeAlumnos");
            modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
    	}
        return modelView;;
    }
}

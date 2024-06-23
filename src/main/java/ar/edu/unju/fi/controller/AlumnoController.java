package ar.edu.unju.fi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.service.AlumnoService;

@Controller
public class AlumnoController {
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("nuevoAlumno", nuevoAlumnoDTO);	
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
	public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoDTOParaGuardar) {
					
		alumnoService.guardarAlumno(alumnoDTOParaGuardar);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());	
		
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
		AlumnoDTO alumnoDTO = alumnoService.buscarAlumno(lu);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", alumnoDTO);
        modelView.addObject("flag", true);
        return modelView;
    }

    @PostMapping("/modificarAlumno")
    public ModelAndView modificarAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoDTOModificado) {
        alumnoService.modificarAlumno(alumnoDTOModificado);
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
        return modelView;
    }
    
}

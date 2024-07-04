package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {
	@Autowired
	Carrera nuevaCarrera;

	@Autowired
	CarreraService carreraService;

	@Autowired
	AlumnoService alumnoService;

	@Autowired
	MateriaService materiaService;

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera", nuevaCarrera);
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		modelView.addObject("flag", false);
		return modelView;
	}

	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraParaGuardar,
			BindingResult resultado) {

		ModelAndView modelView = new ModelAndView();

		if (resultado.hasErrors()) {
			modelView.setViewName("formCarrera");
			modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
			modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
			modelView.addObject("flag", false);
		} else {
			try {
				carreraParaGuardar.getAlumnos().forEach(a -> a.setCarrera(carreraParaGuardar));
				carreraParaGuardar.getMaterias().forEach(m -> m.setCarrera(carreraParaGuardar));
				carreraService.guardarCarrera(carreraParaGuardar);
			} catch (Exception e) {
				modelView.addObject("errors", true);
				modelView.addObject("cargaCarreraErrorMessage", "Error al cargar en la BD" + e.getMessage());
				System.out.println(e.getMessage());
			}
			
			modelView.setViewName("listaDeCarreras");
			modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		}

		return modelView;
	}

	@GetMapping("/listadoCarreras")
	public ModelAndView getFormListaCarrera() {
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		return modelView;
	}

	@GetMapping("/borrarCarrera/{codigo}")
	public ModelAndView deleteCarreraDelListado(@PathVariable(name = "codigo") Integer codigo) {

		carreraService.borrarCarrera(codigo);
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());

		return modelView;
	}

	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView getFormModificarCarrera(@PathVariable(name = "codigo") Integer codigo) {
		Carrera carrera = carreraService.buscarCarrera(codigo);
		carreraService.borrarRelaciones(carrera);
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera", carrera);
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
		modelView.addObject("flag", true);
		return modelView;
	}

	@PostMapping("/modificarCarrera")
	public ModelAndView modificarCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera carreraModificada,
			BindingResult resultado) {

		ModelAndView modelView = new ModelAndView();

		if (resultado.hasErrors()) {
			//carreraService.borrarRelaciones(carreraModificada);
			modelView.setViewName("formCarrera");
			modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
			modelView.addObject("listadoMaterias", materiaService.mostrarMaterias());
			modelView.addObject("flag", true);
		} else {
			try {
				carreraModificada.getAlumnos().forEach(a -> a.setCarrera(carreraModificada));
				carreraModificada.getMaterias().forEach(m -> m.setCarrera(carreraModificada));
				carreraService.guardarCarrera(carreraModificada);
			} catch (Exception e) {
				modelView.addObject("errors", true);
				modelView.addObject("cargaCarreraErrorMessage", "Error al cargar en la BD" + e.getMessage());
				System.out.println(e.getMessage());
			}
			modelView.setViewName("listaDeCarreras");
			modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		}

		return modelView;
	}

	@GetMapping("/alumnosPorCarrera/{codigo}")
	public ModelAndView getFormAlumnosPorCarreraB(@PathVariable(name = "codigo") Integer codigo) {
		ModelAndView modelView = new ModelAndView("alumnosXcarrera");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		modelView.addObject("listadoCarreras", carreraService.mostrarCarreras());
		modelView.addObject("carrera", carreraService.buscarCarrera(codigo));

		return modelView;
	}

}
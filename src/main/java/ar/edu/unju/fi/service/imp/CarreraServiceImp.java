package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	MateriaService materiaService;
	
	@Override
	public void guardarCarrera(Carrera carrera) {
		carreraRepository.save(carrera);
	}

	@Override
	public List<CarreraDTO> mostrarCarreras() {
		return carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO
				(carreraRepository.findCarreraByEstado(true)); 
	}

	@Override
	public void borrarCarrera(Integer codigo) {
		List<Carrera> carreras = carreraRepository.findAll();
		carreras.forEach(carrera -> {
			if(carrera.getCodigo().equals(codigo)) {
				carrera.getAlumnos().forEach(a -> a.setCarrera(null));
				carrera.getMaterias().forEach(m ->m.setCarrera(null));
				carrera.setEstado(false);
				carreraRepository.save(carrera);
			}
		});
	}

	@Override
	public Carrera buscarCarrera(Integer codigo) {
		List<Carrera> carreras = carreraRepository.findAll();
		for (Carrera carrera : carreras) {
			if(carrera.getCodigo().equals(codigo)) {
				return carrera;
			}
		}
		return null;
	}

	@Override
	public void modificarCarrera(Carrera carrera) {
		carreraRepository.save(carrera);
	}

	@Override
	public void borrarRelaciones(Carrera carrera) {
		carrera.getAlumnos().forEach(a -> a.setCarrera(null));
		carrera.getMaterias().forEach(m -> m.setCarrera(null));
		carrera.getAlumnos().clear();
		carrera.getMaterias().clear();
		carreraRepository.save(carrera);
	}
	
}
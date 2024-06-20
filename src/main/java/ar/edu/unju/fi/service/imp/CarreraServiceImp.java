package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Override
	public void guardarCarrera(Carrera carrera) {
		if(!carreraRepository.existsById(carrera.getCodigo())) {
			carreraRepository.save(carrera);
		}
	}

	@Override
	public List<Carrera> mostrarCarreras() {
		return carreraRepository.findCarreraByEstado(true);
	}

	@Override
	public void borrarCarrera(String codigo) {
		List<Carrera> carreras = carreraRepository.findAll();
		carreras.forEach(carrera -> {
			if(carrera.getCodigo().equals(codigo)) {
				carrera.setEstado(false);
				carreraRepository.save(carrera);
			}
		});
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		return carreraRepository.getReferenceById(codigo);
	}

	@Override
	public void modificarCarrera(Carrera carrera) {
		carreraRepository.save(carrera);
	}
	
}
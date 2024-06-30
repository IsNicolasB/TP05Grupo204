package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
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
	public void borrarCarrera(String codigo) {
		List<Carrera> carreras = carreraRepository.findAll();
		carreras.forEach(carrera -> {
			if(carrera.getCodigo().equals(Integer.parseInt(codigo))) {
				carrera.setEstado(false);
				carreraRepository.save(carrera);
			}
		});
	}

	@Override
	public Carrera buscarCarrera(Integer codigo) {
		return carreraRepository.getReferenceById(codigo);
	}

	@Override
	public void modificarCarrera(Carrera carrera) {
		carreraRepository.save(carrera);
	}
	
}
package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Override
	public void guardarCarrera(CarreraDTO carreraDTO) {
		if(!carreraRepository.existsById(carreraDTO.getCodigo())) {
			carreraRepository.save
			(carreraMapDTO.convertirCarreraDTOACarrera(carreraDTO));
		}
	}

	@Override
	public List<CarreraDTO> mostrarCarreras() {
		return carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO
				(carreraRepository.findCarreraByEstado(true)); 
	}

	@Override
	public void borrarCarrera(String codigo) {
		List<CarreraDTO> carrerasDTO = carreraMapDTO.
		convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findAll());
		carrerasDTO.forEach(carreradto -> {
			if(carreradto.getCodigo().equals(codigo)) {
				carreradto.setEstado(false);
				carreraRepository.save
				(carreraMapDTO.convertirCarreraDTOACarrera(carreradto));
			}
		});
	}

	@Override
	public CarreraDTO buscarCarrera(String codigo) {
		return carreraMapDTO.convertirAcarreraDTO
				(carreraRepository.getReferenceById(codigo)); 
	}

	@Override
	public void modificarCarrera(CarreraDTO carreraDTO) {
		carreraRepository.save
		(carreraMapDTO.convertirCarreraDTOACarrera(carreraDTO));
	}
	
}
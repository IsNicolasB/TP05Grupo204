package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Service
public interface CarreraService{
	public void guardarCarrera(Carrera carrera);
	public List<CarreraDTO> mostrarCarreras();
	public void borrarCarrera(String codigo);
	public Carrera buscarCarrera(Integer codigo);
	public void modificarCarrera(Carrera carrera);
}

package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;

@Service
public interface CarreraService{
	public void guardarCarrera(CarreraDTO carreraDTO);
	public List<CarreraDTO> mostrarCarreras();
	public void borrarCarrera(String codigo);
	public CarreraDTO buscarCarrera(String codigo);
	public void modificarCarrera(CarreraDTO carreraDTO);
}

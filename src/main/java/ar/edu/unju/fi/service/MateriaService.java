package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;

@Service
public interface MateriaService {
	
	public void guardarMateria(Materia materia);
	public List<Materia> mostrarMaterias();
	public void borrarMateria (String codigo);
	public void modificarMateria (Materia materia);
	public Materia buscarMateria(String codigo);
}

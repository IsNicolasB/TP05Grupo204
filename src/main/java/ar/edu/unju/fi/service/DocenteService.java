package ar.edu.unju.fi.service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DocenteService {
	
	public void guardarDocente (DocenteDTO docente);
	public List<Docente> mostrarDocentes();
	public void borrarDocente(String legajo);
	public void modificarDocente(Docente docenteModificado);
	public Docente buscarDocente(String legajo);
}

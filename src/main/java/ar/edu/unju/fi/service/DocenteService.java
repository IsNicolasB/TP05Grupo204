package ar.edu.unju.fi.service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DocenteService {
	
	 public void guardarDocente (DocenteDTO docente);
	 public List<DocenteDTO> mostrarDocentes();
	 public void borrarDocente(String legajo);
	 public void modificarDocente(DocenteDTO docenteModificadoDTO);
	 public DocenteDTO buscarDocente(String legajo);
}

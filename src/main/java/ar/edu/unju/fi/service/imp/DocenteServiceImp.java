package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService{

	@Autowired
	DocenteRepository docenteRepository;
	@Autowired
	DocenteMapDTO docenteMapDTO;
	
	@Override
	public void guardarDocente(DocenteDTO docenteDTO) {
		docenteMapDTO.convertirDocenteDTOADocente(docenteDTO);
		if(!docenteRepository.existsById(docenteDTO.getLegajo())) {
		docenteRepository.save
		(docenteMapDTO.convertirDocenteDTOADocente(docenteDTO));
		}
	}

	@Override
	public List<DocenteDTO> mostrarDocentes() {
		return docenteMapDTO.convertirListaDocentesAListaDocentesDTO
				(docenteRepository.findDocenteByEstado(true)); 
	}

	@Override
	public void borrarDocente(String legajo) {
		List<DocenteDTO> todosLosDocentesDTO = docenteMapDTO.
		convertirListaDocentesAListaDocentesDTO(docenteRepository.findAll());
		
		todosLosDocentesDTO.stream()
	    .filter(docente -> docente.getLegajo().equals(legajo))
	    .forEach(docente -> {docente.setEstado(false);
	    docenteRepository.save
	    (docenteMapDTO.convertirDocenteDTOADocente(docente));});
		
	}

	@Override
	public void modificarDocente(DocenteDTO docenteModificadoDTO) {
		// TODO Auto-generated method stub
		docenteRepository.save
		(docenteMapDTO.convertirDocenteDTOADocente(docenteModificadoDTO));
	}

	@Override
	public DocenteDTO buscarDocente(String legajo) {
		return docenteMapDTO.convertirDocenteADocenteDTO
				(docenteRepository.getReferenceById(legajo));
	}

}

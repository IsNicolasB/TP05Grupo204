package ar.edu.unju.fi.service.imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.dto.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;

	@Override
	public void guardarAlumno(AlumnoDTO alumnoDTO) {
		if(!alumnoRepository.existsById(alumnoDTO.getLu())) {
			//alumno.setEstado(true);
			alumnoRepository.save(
			alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoDTO));
		}
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnos() {
		return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO
				(alumnoRepository.findAlumnoByEstado(true)); 
	}

	@Override
	public void borrarAlumno(String lu) {
		List<AlumnoDTO> alumnosDTO = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAll());
		alumnosDTO.forEach(adto -> {
			if(adto.getLu().equals(lu)) {
				adto.setEstado(false);
				alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(adto));
			}
		});
	}

	@Override
	public void modificarAlumno(AlumnoDTO alumnoDTOModificado) {
//		int i=0;
//		List<Alumno> alumnos = alumnoRepository.findAll();
//		for (Alumno alumno : alumnos) {
//			if (alumno.getDni().equals(alumnoModificado.getDni())) {
//				
//			}
//			i++;
//		}
		alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoDTOModificado));
	}

	@Override
	public AlumnoDTO buscarAlumno(String lu) {
		return alumnoMapDTO.convertirAlumnoAAlumnoDTO(alumnoRepository.getReferenceById(lu)); 
	}
	
}

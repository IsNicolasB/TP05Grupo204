package ar.edu.unju.fi.service.imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.AlumnoService;
import jakarta.transaction.Transactional;

@Service
public class AlumnoServiceImp implements AlumnoService{
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	MateriaRepository materiaRepository;

    @Override
    @Transactional
    public Alumno inscribirMateria(String alumnoId, String materiaCodigo) {
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Materia materia = materiaRepository.findById(materiaCodigo).orElseThrow(() -> new RuntimeException("Materia no encontrada"));
        alumno.getMaterias().add(materia);
        materia.getAlumnos().add(alumno);
        alumnoRepository.save(alumno);
        return alumno;
    }

	@Override
	public boolean guardarAlumno(Alumno alumno) {
		if(!alumnoRepository.existsById(alumno.getLu())) {
			//alumno.setEstado(true);
			alumnoRepository.save(alumno);
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnos() {
		return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO
				(alumnoRepository.findAlumnoByEstado(true)); 
	}

	@Override
	public void borrarAlumno(String lu) {
		alumnoRepository.findAll().forEach(a -> {
			if(a.getLu().equals(lu)) {
				a.setEstado(false);
				alumnoRepository.save(a);
			}
		});
	}

	@Override
	public void modificarAlumno(Alumno alumnoModificado) {
//		int i=0;
//		List<Alumno> alumnos = alumnoRepository.findAll();
//		for (Alumno alumno : alumnos) {
//			if (alumno.getDni().equals(alumnoModificado.getDni())) {
//				
//			}
//			i++;
//		}
		alumnoRepository.save(alumnoModificado);
	}

	@Override
	public Alumno buscarAlumno(String lu) {
		return alumnoRepository.getReferenceById(lu); 
	}
	
}

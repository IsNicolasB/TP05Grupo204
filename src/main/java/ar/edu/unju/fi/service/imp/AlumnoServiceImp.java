package ar.edu.unju.fi.service.imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{
	@Autowired
	AlumnoRepository alumnoRepository;

	@Override
	public void guardarAlumno(Alumno alumno) {
		if(!alumnoRepository.existsById(alumno.getLU())) {
			alumno.setEstado(true);
			alumnoRepository.save(alumno);
		}
	}

	@Override
	public List<Alumno> mostrarAlumnos() {
		return alumnoRepository.findAlumnoByEstado(true);
	}

	@Override
	public void borrarAlumno(String LU) {
//		List<Alumno> alumnos = alumnoRepository.findAll();
//		for (Alumno alumno : alumnos) {
//			if (alumno.getDni().equals(dni)) {
//				alumno.setEstado(false);
//				alumnoRepository.save(alumno);
//			    break;
//			}
//		}
		alumnoRepository.getReferenceById(LU).setEstado(false);
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
	public Alumno buscarAlumno(String LU) {
		return alumnoRepository.getReferenceById(LU);
	}
	
}

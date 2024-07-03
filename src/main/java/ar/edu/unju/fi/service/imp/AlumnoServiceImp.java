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

@Service
public class AlumnoServiceImp implements AlumnoService{
	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	MateriaRepository materiaRepository;

    @Override
    public void inscribirMateria(Alumno alumno) {
    	alumnoRepository.save(alumno);
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
				borrarRelaciones(a);
				a.setEstado(false);
				a.getMaterias().forEach(m -> m.getAlumnos().removeIf(c -> c.getLu().equals(lu)));
				a.getMaterias().clear();
				alumnoRepository.save(a);
			}
		});
	}

	@Override
	public void modificarAlumno(Alumno alumnoModificado) {
		alumnoRepository.save(alumnoModificado);
	}

	@Override
	public Alumno buscarAlumno(String lu) {
		return alumnoRepository.getReferenceById(lu); 
	}
	
	@Override
	public void borrarRelaciones(Alumno alumno) {
		alumno.getCarrera().getAlumnos().removeIf(e -> e.getLu().equals(alumno.getLu()));
		alumno.setCarrera(null);
	}
	
}

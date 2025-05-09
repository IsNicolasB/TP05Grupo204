package ar.edu.unju.fi.service;
import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Service
public interface AlumnoService {
	public boolean guardarAlumno(Alumno alumno);
	public List<AlumnoDTO> mostrarAlumnos();
	public void borrarAlumno(String lu);
	public void modificarAlumno(Alumno alumnoModificado);
	public Alumno buscarAlumno(String lu);
	public void inscribirMateria(Alumno alumno);
	public void borrarRelaciones(Alumno alumno);
}

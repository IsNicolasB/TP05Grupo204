package ar.edu.unju.fi.service;
import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.dto.AlumnoDTO;

@Service
public interface AlumnoService {
	public void guardarAlumno(AlumnoDTO alumnoDTO);
	public List<AlumnoDTO> mostrarAlumnos();
	public void borrarAlumno(String lu);
	public void modificarAlumno(AlumnoDTO alumnoDTOModificado);
	public AlumnoDTO buscarAlumno(String lu);
}

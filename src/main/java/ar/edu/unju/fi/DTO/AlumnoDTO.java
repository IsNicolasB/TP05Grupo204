package ar.edu.unju.fi.DTO;
import java.util.List;
import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class AlumnoDTO {
	private String lu;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private Carrera carrera;
	private List<Materia> materias;
}

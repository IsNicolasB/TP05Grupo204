package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class MateriaDTO {
	
	private String nombre;
	private int cantidadHoras;
	private String codigo;
	private String curso;
	private String modalidad;
	private Boolean estado;
//	private Docente docente;
//	private Carrera carrera;
}

package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component

public class CarreraDTO {
	
	private String codigo;
	private String nombre;
	private Integer duracion;
	private List<Alumno> alumnos;
	private List<Materia> materias;
//	private Boolean estado;
}

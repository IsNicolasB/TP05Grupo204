package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Component
@Entity
public class Materia {
	
	@Id
	private String codigo;
	private String nombre;
	private String curso;
	private int cantidadHoras;
	private String modalidad;
	private Boolean estado;
//	private Docente docente;
//	private Carrera carrera;
	
}

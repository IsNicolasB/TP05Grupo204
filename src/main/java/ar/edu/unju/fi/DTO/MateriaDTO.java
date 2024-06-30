package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
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
	@ManyToOne
	private CarreraDTO carrera;
	@ManyToOne
	private DocenteDTO docente;
}

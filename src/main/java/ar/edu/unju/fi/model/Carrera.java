package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@NotBlank(message="Ingrese el Nombre")
	@Size(min=3, max= 60,message="El Nombre debe tener entre 3 y 60 caracteres")
	private String nombre;
	
	@NotNull(message="Ingrese la Duración de la Carrera")
	@Min(value = 3 , message="La duracion debe ser mayor a 3 y menor a 6")
	@Max(value = 6 , message="La duracion debe ser menor a 3 y menor a 6")
	private Integer duracion;
	
	@NotNull(message="Ingrese el Estado del Alumno")
	private Boolean estado;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL)
	private List<Alumno> alumnos;
	
	@OneToMany(mappedBy="carrera", cascade=CascadeType.ALL)
	private List<Materia> materias;
}

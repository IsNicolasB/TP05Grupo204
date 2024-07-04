package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Materia{
	
	@Id
	@Column(name = "m_materia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@NotBlank(message="Debe ingresar un nombre para la materia")
	@Size(min=3, max=20, message="El nombre de la materia debe contener minimo 3 caracteres y maximo 20")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe Que ingresar únicamente letras")
	private String nombre;
	@NotBlank(message="Debe ingresar el curso")
	@Size(min=3, max=20, message="El nombre del curso debe contener minimo 3 caracteres y maximo 20")
	@Pattern(regexp= "[a-z A-Z 1-9]*", message="Debe ingresar únicamente letras")
	private String curso;
	@NotNull(message="Ingrese una cantidad de horas para la materia")
	@Min(value=40, message="El minimo de horas es 40")
	@Max(value=240, message="El maximo de horas es 320")
	private int cantidadHoras;
	@NotBlank(message = "Debe ingresar la modalidad")
	@Pattern(regexp= "Presencial|Virtual", message=" La modalidad debe ser 'Presencial' o 'Virtual'")
	private String modalidad;
	@NotNull(message= "Eliga un estado para la materia")
	private Boolean estado;
	
	@ManyToMany (mappedBy= "materias")
	private List<Alumno> Alumnos;
	
	@OneToOne
	@NotNull(message= "Elija un docente para la materia")
	@JoinColumn(name = "m_leg", referencedColumnName = "legajo", unique=true)
	private Docente docente;
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="c_cod")
	Carrera carrera;
	
}
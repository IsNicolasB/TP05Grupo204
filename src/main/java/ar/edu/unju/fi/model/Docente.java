package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Docente {
	
	@Id
	@NotBlank(message="Debe ingresar un legajo")
	private String legajo;
	
	@NotBlank(message="Debe ingresar un nombre")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	@Size(min=2, max=20,message="El nombre no puede llevar menos de 2 caracteres y más de 20 caracteres")
	private String nombre;
	
	@NotBlank(message="Debe ingresar un apellido")
	@Pattern(regexp= "[a-z A-Z]*", message="Debe ingresar únicamente letras")
	@Size(min=2, max=20,message="El apellido no puede llevar menos de 2 caracteres y más de 20 caracteres")
	private String apellido;
	
	@Email
	private String email;
	
	@Size(min=10, max=10,message="Ingrese un telefono valido (10 caracteres)")
	private String telefono;
	private boolean estado;
}

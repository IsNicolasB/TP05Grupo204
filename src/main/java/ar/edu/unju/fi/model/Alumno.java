package ar.edu.unju.fi.model;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Component
@Entity
public class Alumno {
	@Id
	private String LU;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String fechaNacimiento;
	private String domicilio;
	private boolean estado;
}

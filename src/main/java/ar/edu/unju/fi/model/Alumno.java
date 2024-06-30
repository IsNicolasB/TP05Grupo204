package ar.edu.unju.fi.model;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Component
@Entity
public class Alumno {
	@Id
	@NotBlank(message="Ingrese la Libreta Universitaria")
	@Size(min=9, max=9, message="La Libreta Universitaria debe tener 9 caracteres")
	private String lu;
	@NotBlank(message="Ingrese el DNI")
	@Size(min=8, max=8, message="El debe tener 8 digitos")
	@Pattern(regexp="[0-9]*", message="DNI Invalido. Utilize solo numeros")
	private String dni;
	@NotBlank(message="Ingrese el Nombre")
	private String nombre;
	@NotBlank(message="Ingrese el Apellido")
	private String apellido;
	@NotBlank(message="Ingrese el Email")
	@Email(message="Ingrese un Email valido")
	private String email;
	@NotBlank(message="Ingrese el Telefono Fijo")
	@Size(min=7, max=7, message="El telefono fijo debe tener 7 digitos")
	@Pattern(regexp="[0-9]*", message="Telefono Invalido. Utilize solo numeros")
	private String telefono;
	@NotNull(message="Ingrese la Fecha de Nacimiento")
	@Past(message="Ingrese una fecha anterior a la actual")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate fechaNacimiento;
	@NotBlank(message="Ingrese el Domicilio actual")
	private String domicilio;
	@NotNull(message="Ingrese el Estado del Alumno")
	private boolean estado;
}

package ar.edu.unju.fi.model;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	@Size(min=8, max=8, message="El DNI debe tener 8 digitos")
	@Pattern(regexp="[0-9]*", message="DNI Invalido. Utilize solo numeros")
	private String dni;
	@NotBlank(message="Ingrese el Nombre")
	@Size(max=30, message="Nombre muy largo")
	private String nombre;
	@NotBlank(message="Ingrese el Apellido")
	@Size(max=20, message="Apellido muy largo")
	private String apellido;
	@NotBlank(message="Ingrese el Email")
	@Size(max=40, message="Email muy largo")
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
	@Size(max=60, message="Domicilio muy largo")
	private String domicilio;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_cod")
	private Carrera carrera;
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="alumno_materias")
	private List<Materia> materias;
	@NotNull(message="Ingrese el Estado del Alumno")
	private boolean estado;
}

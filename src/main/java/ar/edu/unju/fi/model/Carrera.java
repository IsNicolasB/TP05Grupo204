package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Component
@Entity
public class Carrera {
	@Id
	private String codigo;
	private String nombre;
	private Integer duracion;
	private Boolean estado;
}

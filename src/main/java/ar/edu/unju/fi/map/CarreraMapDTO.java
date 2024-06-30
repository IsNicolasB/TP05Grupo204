package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapDTO {
	@Mapping(source="codigo", target="codigo")
	@Mapping(source="nombre", target="nombre")
	@Mapping(source="duracion", target="duracion")
	@Mapping(source="alumnos", target="alumnos")
	@Mapping(source="materias", target="materias")
	//@Mapping(source="estado", target="estado")
	CarreraDTO convertirAcarreraDTO(Carrera carrera);
	
	@Mapping(target = "estado", ignore = true)
	@InheritInverseConfiguration
	Carrera convertirCarreraDTOACarrera (CarreraDTO carreradto);

	List<CarreraDTO> convertirListaCarrerasAListaCarrerasDTO (List<Carrera> listaCarrera);

	List<Carrera> convertirListaCarrerasDTOAListaCarrerass (List<CarreraDTO> listaCarreraDTO);
}

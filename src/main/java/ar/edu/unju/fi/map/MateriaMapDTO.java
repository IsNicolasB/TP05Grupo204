package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapDTO {
	
	@Mapping(source="nombre", target="nombre")
	@Mapping(source="cantidadHoras", target="cantidadHoras")
	@Mapping(source="codigo", target="codigo")
	@Mapping(source="curso", target="curso")
	@Mapping(source="estado", target="estado")
	@Mapping(source="modalidad", target="modalidad")
	@Mapping(source="carrera", target="carrera")
	@Mapping(source="docente", target="docente")
	MateriaDTO convertirMateriaAMateriaDTO(Materia m);
	
	@InheritInverseConfiguration
	Materia convertirMateriaDTOAMateria(MateriaDTO m);
	
	List<MateriaDTO> convertirListaMateriaAListaMateriaDTO (List<Materia> listaM);
	
	List<Materia> convertirListaMateriaDTOAListaMateria (List<MateriaDTO> listaMDTO);
	
}

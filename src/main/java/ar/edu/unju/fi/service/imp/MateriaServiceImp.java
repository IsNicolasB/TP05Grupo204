package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService{
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	MateriaMapDTO materiaMapDto;
	
	@Override
	public void guardarMateria(MateriaDTO materiaDTO) {
		// TODO Auto-generated method stub
		
		
		
		if (!materiaRepository.existsById(materiaDTO.getCodigo())) {
			materiaRepository.save(
			materiaMapDto.convertirMateriaDTOAMateria(materiaDTO));
		}
	}

	@Override
	public List<MateriaDTO> mostrarMaterias() {
		// TODO Auto-generated method stub
		
		return materiaMapDto.convertirListaMateriaAListaMateriaDTO(materiaRepository.findMateriaByEstado(true));
	}

	@Override
	public void borrarMateria(String codigo) {
		List<MateriaDTO> materias = materiaMapDto.convertirListaMateriaAListaMateriaDTO(materiaRepository.findAll());
		materias.forEach(materia -> {
			if(materia.getCodigo().equals(codigo)) {
				materia.setEstado(false);
				materiaRepository.save(materiaMapDto.convertirMateriaDTOAMateria(materia));
			}
		});
	}

	@Override
	public MateriaDTO buscarMateria(String codigo) { //MateriaDTO
		// TODO Auto-generated method stub
		List<MateriaDTO> todasLasMaterias = materiaMapDto.convertirListaMateriaAListaMateriaDTO(materiaRepository.findAll());
		for (MateriaDTO materias : todasLasMaterias) {
			if(materias.getCodigo().equals(codigo)) {
				return materias;
			}
		}
		return null;
	}

	@Override
	public void modificarMateria(MateriaDTO materiaModificada) {
		// TODO Auto-generated method stub
		materiaModificada.setEstado(true);
		materiaRepository.save(materiaMapDto.convertirMateriaDTOAMateria(materiaModificada));
	}

}

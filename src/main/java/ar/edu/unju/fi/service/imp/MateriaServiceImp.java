package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService{
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Override
	public void guardarMateria(Materia materia) {
		// TODO Auto-generated method stub
		if (!materiaRepository.existsById(materia.getCodigo())) {
			materiaRepository.save(materia);
		}
	}

	@Override
	public List<Materia> mostrarMaterias() {
		// TODO Auto-generated method stub
		
		return materiaRepository.findMateriaByEstado(true);
	}

	@Override
	public void borrarMateria(String codigo) {
		List<Materia> carreras = materiaRepository.findAll();
		carreras.forEach(carrera -> {
			if(carrera.getCodigo().equals(codigo)) {
				carrera.setEstado(false);
				materiaRepository.save(carrera);
			}
		});
	}

	@Override
	public Materia buscarMateria(String codigo) {
		// TODO Auto-generated method stub
		List<Materia> todasLasMaterias = materiaRepository.findAll();
		for (Materia materias : todasLasMaterias) {
			if(materias.getCodigo().equals(codigo)) {
				return materias;
			}
		}
		return null;
	}

	@Override
	public void modificarMateria(Materia materiaModificada) {
		// TODO Auto-generated method stub
		materiaModificada.setEstado(true);
		materiaRepository.save(materiaModificada);
	}

}

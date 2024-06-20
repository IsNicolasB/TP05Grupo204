package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService{

	@Autowired
	DocenteRepository docenteRepository;
	
	@Override
	public void guardarDocente(Docente docente) {
		// TODO Auto-generated method stub
		if(!docenteRepository.existsById(docente.getLegajo()))
		docenteRepository.save(docente);	
	}

	@Override
	public List<Docente> mostrarDocentes() {
		// TODO Auto-generated method stub
		return docenteRepository.findDocenteByEstado(true);
	}

	@Override
	public void borrarDocente(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		todosLosDocentes.stream()
	    .filter(docente -> docente.getLegajo().equals(legajo))
	    .forEach(docente -> {docente.setEstado(false);
	    docenteRepository.save(docente);});
		
	}

	@Override
	public void modificarDocente(Docente docenteModificado) {
		// TODO Auto-generated method stub
		docenteRepository.save(docenteModificado);
	}

	@Override
	public Docente buscarDocente(String legajo) {
		// TODO Auto-generated method stub
		return docenteRepository.getReferenceById(legajo);
	}

}

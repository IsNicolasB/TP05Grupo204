package ar.edu.unju.fi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, String> {
	List<Materia> findMateriaByEstado(boolean estado);
}

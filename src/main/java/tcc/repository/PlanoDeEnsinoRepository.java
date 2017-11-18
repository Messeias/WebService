package tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.model.PlanoDeEnsino;


public interface PlanoDeEnsinoRepository extends JpaRepository<tcc.model.PlanoDeEnsino, Long> {
	
	@Query("SELECT p FROM Materia m JOIN m.planoDeEnsino p WHERE m.codMateria = ?")
	PlanoDeEnsino buscarPorMateria(Long id);

}

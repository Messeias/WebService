package tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	@Query("SELECT t FROM Tarefa t INNER JOIN t.materia m ON m.codMateria = t.materia WHERE m.codMateria IN (SELECT c.materia.codMateria FROM Convite c WHERE c.usuario.codUsuario = ?)")
	List<Tarefa> findByAluno(Long idAluno);

}

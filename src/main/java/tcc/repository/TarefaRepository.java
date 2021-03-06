package tcc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	
	@Query("SELECT t FROM Tarefa t INNER JOIN t.materia m ON m.codMateria = t.materia WHERE m.codMateria IN (SELECT c.materia.codMateria FROM Convite c WHERE c.usuario.codUsuario = ?)")
	List<Tarefa> findByAluno(Long idAluno);

	@Query("SELECT t FROM Tarefa t INNER JOIN t.materia m ON m.codMateria = t.materia WHERE t.dataEntrega = ? AND t.materia.codMateria IN " + 
			"(SELECT m2.codMateria FROM Convite c INNER JOIN c.materia m2  WHERE c.usuario.codUsuario = ?)")
	List<Tarefa> findByData(Date data, long codUsuario);
	
	
	@Query("SELECT t FROM Tarefa t INNER JOIN t.materia m ON m.codMateria = t.materia WHERE m.codMateria = ?")
	List<Tarefa> findByMateria(Long idMateria);
	
	@Query("SELECT t FROM Tarefa t INNER JOIN t.materia m ON m.codMateria = t.materia WHERE t.dataEntrega = ? AND t.materia.codMateria = ?")
	List<Tarefa> findByDataMateria(Date data, long codMateria);
}


// ON c.materia.codMateria = m2.codMateria
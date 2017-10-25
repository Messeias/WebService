package tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.model.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long>{
	
	//@Query("SELECT m FROM Materia m WHERE m.nome LIKE ?")
    List<Materia> findByNomeContaining(String nome);
    
    @Query("SELECT m FROM Convite c INNER JOIN c.materia m ON m.codMateria = c.materia WHERE c.usuario.codUsuario = ?")
	List<Materia> findByAluno(Long id);
}

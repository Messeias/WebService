package tcc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tcc.model.Convite;
import tcc.model.Materia;

public interface ConviteRepository extends JpaRepository<Convite, Long>{
	
//	@Query("SELECT m FROM Convite c INNER JOIN c.Materia m ON m.cod_materia = c._materia_cod_materia WHERE c.usuario_cod_usuario = ?")
//	List<Materia> buscarMateriaPorAluno(int id);
}

package tcc.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import tcc.model.Anotacao;
import tcc.model.Materia;
import tcc.repository.AnotacaoRepository;
import tcc.repository.MateriaRepository;

@Controller
@RequestMapping(value="/materia")
public class MateriaController {
	@Autowired
	private MateriaRepository materiaRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Materia>> findAll(){
		List<Materia> materias = materiaRepository.findAll();
		
		if(materias.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Materia>>(materias, HttpStatus.OK);
	}
	
	@RequestMapping(value="pesquisar", method = RequestMethod.GET)
	public ResponseEntity<List<Materia>> findAllPublic(){
		List<Materia> materias = materiaRepository.findByPublico(1);
		
		if(materias.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Materia>>(materias, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{codMateria}", method=RequestMethod.GET)
	public ResponseEntity<Materia> findById(@PathVariable("codMateria") Long codMateria){
		Materia m = materiaRepository.findOne(codMateria);
		
		if(m == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Materia>(m, HttpStatus.OK);
	}
	
	@RequestMapping(value="/buscar/{nome}", method=RequestMethod.GET)
	public ResponseEntity<List<Materia>> findByNome(@PathVariable("nome") String nome){
		List<Materia> m = materiaRepository.findByNomeContainingAndPublico(nome, true);
		
		if(m == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Materia>>(m, HttpStatus.OK);
	}
	
	@RequestMapping(value="/usuario/{idUsuario}", method=RequestMethod.GET)
	public ResponseEntity<List<Materia>> findByAluno(@PathVariable("idUsuario") Long id){
		List<Materia> m = materiaRepository.findByAluno(id);
		
//		System.out.println(m.get(0).getHorarios().get(0).getHora().toString());
		
		if(m == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Materia>>(m, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Materia> save(@RequestBody Materia m){
		Materia cadastrando = materiaRepository.save(m);
		return new ResponseEntity<Materia>(cadastrando, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codMateria}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codMateria") Long codMateria){
		Materia deletada = materiaRepository.findOne(codMateria);
		if(deletada == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir a materia com codMateria: " + codMateria),
					HttpStatus.NOT_FOUND);
		}
		materiaRepository.delete(deletada);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	 @RequestMapping(value = "/{codMateria}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateMateria(@PathVariable("codAnotacao") Long codMateria, @RequestBody Materia materia) {
		 Materia currentMateria = materiaRepository.findOne(codMateria);
	 
	        if (currentMateria == null) {
	            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Antoacao com codMateria " + codMateria + " não encontrada."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currentMateria.setAnotacoes(materia.getAnotacoes());
	        currentMateria.setConvites(materia.getConvites());
	        currentMateria.setDescricao(materia.getDescricao());
	        currentMateria.setHorarios(materia.getHorarios());
	        currentMateria.setNome(materia.getNome());
	        currentMateria.setPublico(materia.isPublico());
	        currentMateria.setTarefas(materia.getTarefas());
	        
	        materiaRepository.save(currentMateria);
	        return new ResponseEntity<Materia>(currentMateria, HttpStatus.OK);
	    }
}

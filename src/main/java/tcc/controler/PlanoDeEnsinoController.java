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
import tcc.model.Assunto;
import tcc.model.PlanoDeEnsino;
import tcc.repository.AnotacaoRepository;
import tcc.repository.AssuntoRepository;
import tcc.repository.PlanoDeEnsinoRepository;

@Controller
@RequestMapping(value="/planoDeEnsino")
public class PlanoDeEnsinoController {
	@Autowired
	private PlanoDeEnsinoRepository planoDeEnsinoRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<PlanoDeEnsino>> findAll(){
		List<PlanoDeEnsino> planosDeEnsino = planoDeEnsinoRepository.findAll();
		
		if(planosDeEnsino.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<PlanoDeEnsino>>(planosDeEnsino, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{codPlanoDeEnsino}", method=RequestMethod.GET)
	public ResponseEntity<PlanoDeEnsino> findById(@PathVariable("codPlanoDeEnsino") Long codPlanoDeEnsino){
		PlanoDeEnsino p = planoDeEnsinoRepository.findOne(codPlanoDeEnsino);
		
		if(p == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<PlanoDeEnsino>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/materia/{codMateria}", method=RequestMethod.GET)
	public ResponseEntity<PlanoDeEnsino> buscarPorMateria(@PathVariable("codMateria") Long codMateria){
		// buscar id plano de ensino
		PlanoDeEnsino p = planoDeEnsinoRepository.buscarPorMateria(codMateria);
		
		if(p == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<PlanoDeEnsino>(p, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody PlanoDeEnsino p, UriComponentsBuilder ucb){
		PlanoDeEnsino cadastrando = planoDeEnsinoRepository.save(p);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/assunto/{codPlanoDeEnsino}").buildAndExpand(cadastrando.getCodPlanoDeEnsino()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codPlanoDeEnsino}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codPlanoDeEnsino") Long codPlanoDeEnsino){
		PlanoDeEnsino deletado = planoDeEnsinoRepository.findOne(codPlanoDeEnsino);
		if(deletado == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir o plano de ensino com codPlanoDeEnsino: " + codPlanoDeEnsino),
					HttpStatus.NOT_FOUND);
		}
		planoDeEnsinoRepository.delete(deletado);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	 @RequestMapping(value = "/{codPlanoDeEnsino}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateAssunto(@PathVariable("codPlanoDeEnsino") Long codPlanoDeEnsino, @RequestBody PlanoDeEnsino planoDeEnsino) {
		 PlanoDeEnsino currentPlanoDeEnsino = planoDeEnsinoRepository.findOne(codPlanoDeEnsino);
	 
	        if (currentPlanoDeEnsino == null) {
	            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Plano de ensino com codPlanoDeEnsino " + codPlanoDeEnsino + " não encontrada."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currentPlanoDeEnsino.setAssuntos(planoDeEnsino.getAssuntos());
	        currentPlanoDeEnsino.setProfessor(planoDeEnsino.getProfessor());

	        planoDeEnsinoRepository.save(currentPlanoDeEnsino);
	        return new ResponseEntity<PlanoDeEnsino>(currentPlanoDeEnsino, HttpStatus.OK);
	    }
}

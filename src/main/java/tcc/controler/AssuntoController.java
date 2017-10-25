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
import tcc.repository.AnotacaoRepository;
import tcc.repository.AssuntoRepository;

@Controller
@RequestMapping(value="/assunto")
public class AssuntoController {
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Assunto>> findAll(){
		List<Assunto> assuntos = assuntoRepository.findAll();
		
		if(assuntos.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Assunto>>(assuntos, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{codAssunto}", method=RequestMethod.GET)
	public ResponseEntity<Assunto> findById(@PathVariable("codAnotacao") Long codAssunto){
		Assunto a = assuntoRepository.findOne(codAssunto);
		
		if(a == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Assunto>(a, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Assunto a, UriComponentsBuilder ucb){
		Assunto cadastrando = assuntoRepository.save(a);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/assunto/{codAssunto}").buildAndExpand(cadastrando.getCodAssunto()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codAssunto}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codAssunto") Long codAssunto){
		Assunto deletado = assuntoRepository.findOne(codAssunto);
		if(deletado == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir o assunto com codAssunto: " + codAssunto),
					HttpStatus.NOT_FOUND);
		}
		assuntoRepository.delete(deletado);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	 @RequestMapping(value = "/{codAssunto}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateAssunto(@PathVariable("codAssunto") Long codAssunto, @RequestBody Assunto assunto) {
		 Assunto currentAssunto = assuntoRepository.findOne(codAssunto);
	 
	        if (currentAssunto == null) {
	            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Assunto com codAssunto " + codAssunto + " não encontrada."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currentAssunto.setDataFim(assunto.getDataFim());
	        currentAssunto.setDataInicio(assunto.getDataInicio());
	        currentAssunto.setDescricao(assunto.getDescricao());
	        currentAssunto.setNome(assunto.getNome());
	        currentAssunto.setPlanoDeEnsino(assunto.getPlanoDeEnsino());

	        assuntoRepository.save(currentAssunto);
	        return new ResponseEntity<Assunto>(currentAssunto, HttpStatus.OK);
	    }
}

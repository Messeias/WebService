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
import tcc.repository.AnotacaoRepository;

@Controller
@RequestMapping(value="/anotacao")
public class AnotacaoController {
	@Autowired
	private AnotacaoRepository anotacaoRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Anotacao>> findAll(){
		List<Anotacao> anotacoes = anotacaoRepository.findAll();
		
		if(anotacoes.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Anotacao>>(anotacoes, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{codAnotacao}", method=RequestMethod.GET)
	public ResponseEntity<Anotacao> findById(@PathVariable("codAnotacao") Long codAnotacao){
		Anotacao a = anotacaoRepository.findOne(codAnotacao);
		
		if(a == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Anotacao>(a, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Anotacao a, UriComponentsBuilder ucb){
		Anotacao cadastrando = anotacaoRepository.save(a);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/anotacao/{codAnotacao}").buildAndExpand(cadastrando.getCodAnotacao()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codAnotacao}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codAnotacao") Long codAnotacao){
		Anotacao deletada = anotacaoRepository.findOne(codAnotacao);
		if(deletada == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir a anotação com codAnotacao: " + codAnotacao),
					HttpStatus.NOT_FOUND);
		}
		anotacaoRepository.delete(deletada);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	 @RequestMapping(value = "/{codAnotacao}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateAnotacao(@PathVariable("codAnotacao") Long codAnotacao, @RequestBody Anotacao anotacao) {
	        Anotacao currentAnotacao = anotacaoRepository.findOne(codAnotacao);
	 
	        if (currentAnotacao == null) {
	            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Antoacao com codAnotacao " + codAnotacao + " não encontrada."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currentAnotacao.setMateria(anotacao.getMateria());
	        currentAnotacao.setMidia(anotacao.getMidia());
	        currentAnotacao.setTexto(anotacao.getTexto());
	        currentAnotacao.setTitulo(anotacao.getTitulo());
	        currentAnotacao.setUsuario(anotacao.getUsuario());
	 
	        anotacaoRepository.save(currentAnotacao);
	        return new ResponseEntity<Anotacao>(currentAnotacao, HttpStatus.OK);
	    }
}

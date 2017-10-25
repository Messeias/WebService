package tcc.controler;

import java.util.List;
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

import tcc.model.Convite;
import tcc.repository.ConviteRepository;

@Controller
@RequestMapping(value = "/convite")
public class ConviteController {
	
	@Autowired
	private ConviteRepository conviteRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Convite>> findAll(){
		List<Convite> convites = conviteRepository.findAll();
		
		if(convites.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Convite>>(convites, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{codConvite}", method=RequestMethod.GET)
	public ResponseEntity<Convite> findById(@PathVariable("codConvite") Long codConvite){
		Convite convite = conviteRepository.findOne(codConvite);
		
		if(convite == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Convite>(convite, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Convite convite, UriComponentsBuilder ucb){
		Convite cadastrando = conviteRepository.save(convite);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/convite/{codConvite}").buildAndExpand(cadastrando.getCodConvite()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codConvite}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codConvite") Long codConvite){
		Convite deletada = conviteRepository.findOne(codConvite);
		if(deletada == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir o convite com codConvite: " + codConvite),
					HttpStatus.NOT_FOUND);
		}
		conviteRepository.delete(deletada);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{codConvite}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateConvite(@PathVariable("codConvite") Long codConvite, @RequestBody Convite convite) {
        Convite currentConvite = conviteRepository.findOne(codConvite);
 
        if (currentConvite == null) {
            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Convite com codConvite " + codConvite + " não encontrada."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentConvite.setMateria(convite.getMateria());
        currentConvite.setUsuario(convite.getUsuario());
        currentConvite.setStatus(convite.isStatus());
 
        conviteRepository.save(currentConvite);
        return new ResponseEntity<Convite>(currentConvite, HttpStatus.OK);
    }
}

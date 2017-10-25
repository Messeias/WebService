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

import tcc.model.Horario;
import tcc.repository.HorarioRepository;

@Controller
@RequestMapping(value = "/horario")
public class HorarioController {
	
	@Autowired
	private HorarioRepository horarioRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Horario>> findAll(){
		List<Horario> horarios = horarioRepository.findAll();
		
		if(horarios.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{codhorario}", method=RequestMethod.GET)
	public ResponseEntity<Horario> findById(@PathVariable("codhorario") Long codhorario){
		Horario horario = horarioRepository.findOne(codhorario);
		
		if(horario == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Horario>(horario, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Horario horario, UriComponentsBuilder ucb){
		Horario cadastrando = horarioRepository.save(horario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/horario/{codhorario}").buildAndExpand(cadastrando.getCodHorario()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codhorario}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codhorario") Long codhorario){
		Horario deletada = horarioRepository.findOne(codhorario);
		if(deletada == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir o horario com codhorario: " + codhorario),
					HttpStatus.NOT_FOUND);
		}
		horarioRepository.delete(deletada);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{codhorario}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatehorario(@PathVariable("codhorario") Long codhorario, @RequestBody Horario horario) {
        Horario currenthorario = horarioRepository.findOne(codhorario);
 
        if (currenthorario == null) {
            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Horario com codhorario " + codhorario + " não encontrado."),
                    HttpStatus.NOT_FOUND);
        }
 
        currenthorario.setHora(horario.getHora());
        currenthorario.setMateria(horario.getMateria());
 
        horarioRepository.save(currenthorario);
        return new ResponseEntity<Horario>(currenthorario, HttpStatus.OK);
    }
}

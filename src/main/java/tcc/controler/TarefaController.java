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

import tcc.model.Tarefa;
import tcc.repository.TarefaRepository;

@Controller
@RequestMapping(value="/tarefa")
public class TarefaController {
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Tarefa>> findAll(){
		List<Tarefa> tarefas = tarefaRepository.findAll();
		
		if(tarefas.isEmpty()) 
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Tarefa>>(tarefas, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{codtarefa}", method=RequestMethod.GET)
	public ResponseEntity<Tarefa> findById(@PathVariable("codtarefa") Long codtarefa){
		Tarefa tarefa = tarefaRepository.findOne(codtarefa);
		
		if(tarefa == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Tarefa>(tarefa, HttpStatus.OK);
	}
	
	@RequestMapping(value="aluno/{codAluno}", method=RequestMethod.GET)
	public ResponseEntity<List<Tarefa>> findByAluno(@PathVariable("codAluno") Long codAluno){
		List<Tarefa> tarefas = tarefaRepository.findByAluno(codAluno);
		
		if(tarefas == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Tarefa>>(tarefas, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody Tarefa tarefa, UriComponentsBuilder ucb){
		Tarefa cadastrando = tarefaRepository.save(tarefa);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/tarefa/{codtarefa}").buildAndExpand(cadastrando.getCodTarefa()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codtarefa}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codtarefa") Long codtarefa){
		Tarefa deletada = tarefaRepository.findOne(codtarefa);
		if(deletada == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir a tarefa com codtarefa: " + codtarefa),
					HttpStatus.NOT_FOUND);
		}
		tarefaRepository.delete(deletada);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	 @RequestMapping(value = "/{codtarefa}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updatetarefa(@PathVariable("codtarefa") Long codtarefa, @RequestBody Tarefa tarefa) {
	        Tarefa currenttarefa = tarefaRepository.findOne(codtarefa);
	 
	        if (currenttarefa == null) {
	            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. Antoacao com codtarefa " + codtarefa + " não encontrada."),
	                    HttpStatus.NOT_FOUND);
	        }
	 
	        currenttarefa.setMateria(tarefa.getMateria());
	        currenttarefa.setDataEntrega(tarefa.getDataEntrega());
	        currenttarefa.setDescricao(tarefa.getDescricao());
	        currenttarefa.setEtiqueta(tarefa.getEtiqueta());
	        currenttarefa.setNome(tarefa.getNome());
	        currenttarefa.setPeso(tarefa.getPeso());
	        currenttarefa.setUsuario(tarefa.getUsuario());
	 
	        tarefaRepository.save(currenttarefa);
	        return new ResponseEntity<Tarefa>(currenttarefa, HttpStatus.OK);
	    }
}

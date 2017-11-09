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

import tcc.model.Usuario;
import tcc.repository.UsuarioRepository;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		if(usuarios.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{codusuario}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> findById(@PathVariable("codusuario") Long codusuario){
		Usuario usuario = usuarioRepository.findOne(codusuario);
		
		if(usuario == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@RequestMapping(value="/logar", method=RequestMethod.POST)
	public ResponseEntity<Usuario> findByLoginAndSenha(@RequestBody Usuario usuario){
		Usuario newUsuario = usuarioRepository.findByLoginAndSenha(usuario.getEmail(), usuario.getSenha());
		
		if(usuario == null) 
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Usuario>(newUsuario, HttpStatus.OK);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
		Usuario cadastrando = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(cadastrando, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{codusuario}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("codusuario") Long codusuario){
		Usuario deletada = usuarioRepository.findOne(codusuario);
		if(deletada == null) {
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir o usuario com codusuario: " + codusuario),
					HttpStatus.NOT_FOUND);
		}
		usuarioRepository.delete(deletada);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{codusuario}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateusuario(@PathVariable("codusuario") Long codusuario, @RequestBody Usuario usuario) {
        Usuario currentusuario = usuarioRepository.findOne(codusuario);
 
        if (currentusuario == null) {
            return new ResponseEntity(new CustomErrorType("Nao foi possivel atualizar. usuario com codusuario " + codusuario + " não encontrado."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentusuario.setCelular(usuario.getCelular());
        currentusuario.setEmail(usuario.getEmail());
        currentusuario.setNome(usuario.getNome());
        currentusuario.setSenha(usuario.getSenha());
 
        usuarioRepository.save(currentusuario);
        return new ResponseEntity<Usuario>(currentusuario, HttpStatus.OK);
    }
}

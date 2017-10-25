package tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;


@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codUsuario;
	
	@Column(name="celular", nullable=false)
	private String celular;
	
	@Email(message = "Email invalido")
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@OneToMany(mappedBy="usuario")
	private List<Convite> convites;
	
	@OneToMany(mappedBy="usuario")
	private List<Anotacao> anotacoes;
	
	@OneToMany(mappedBy="usuario")
	private List<Tarefa> tarefas;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String celular, String email, String senha, String nome, List<Convite> convites,
			List<Anotacao> anotacoes, List<Tarefa> tarefas) {
		super();
		this.celular = celular;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.convites = convites;
		this.anotacoes = anotacoes;
		this.tarefas = tarefas;
	}


	public long getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(long codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + (int) (codUsuario ^ (codUsuario >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (codUsuario != other.codUsuario)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}

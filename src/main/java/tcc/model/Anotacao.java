package tcc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Anotacao {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codAnotacao;
	
	@Column(name="titulo", nullable=false)
	private String titulo;
	
	@Column(name="texto", nullable=false)
	private String texto;
	
	@Column(name="midia", nullable=true)
	private byte[] midia;
		
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Materia materia;
	
	public Anotacao() {
		super();
	}

	


	public Anotacao(String titulo, String texto, byte[] midia, Usuario usuario, Materia materia) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.midia = midia;
		this.usuario = usuario;
		this.materia = materia;
	}




	public long getCodAnotacao() {
		return codAnotacao;
	}

	public void setCodAnotacao(long codAnotacao) {
		this.codAnotacao = codAnotacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public byte[] getMidia() {
		return midia;
	}

	public void setMidia(byte[] midia) {
		this.midia = midia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public Materia getMateria() {
		return materia;
	}




	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	
}

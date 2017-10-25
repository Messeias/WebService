package tcc.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarefa {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codTarefa;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="etiqueta", nullable=false)
	private char etiqueta;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataEntrega", nullable=false)
	private Date dataEntrega;
	
	@Column(name="peso", nullable=false)
	private double peso;	
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Materia materia;
	
	public Tarefa() {
		super();
	}

	


	public Tarefa(String nome, char etiqueta, String descricao, Date dataEntrega, double peso, Usuario usuario,
			Materia materia) {
		super();
		this.nome = nome;
		this.etiqueta = etiqueta;
		this.descricao = descricao;
		this.dataEntrega = dataEntrega;
		this.peso = peso;
		this.usuario = usuario;
		this.materia = materia;
	}




	public long getCodTarefa() {
		return codTarefa;
	}

	public void setCodTarefa(long codTarefa) {
		this.codTarefa = codTarefa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(char etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
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

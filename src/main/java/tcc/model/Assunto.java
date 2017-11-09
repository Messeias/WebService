package tcc.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Assunto {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codAssunto;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataInicio", nullable=false)
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dataFim", nullable=false)
	private Date dataFim;
	
//	@JsonIgnore
	@ManyToOne
	private PlanoDeEnsino planoDeEnsino;

	
	
	public Assunto() {
		super();
	}

	public Assunto(String descricao, String nome, Date dataInicio, Date dataFim, PlanoDeEnsino planoDeEnsino) {
		super();
		this.descricao = descricao;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.planoDeEnsino = planoDeEnsino;
	}

	public long getCodAssunto() {
		return codAssunto;
	}

	public void setCodAssunto(long codAssunto) {
		this.codAssunto = codAssunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public PlanoDeEnsino getPlanoDeEnsino() {
		return planoDeEnsino;
	}

	public void setPlanoDeEnsino(PlanoDeEnsino planoDeEnsino) {
		this.planoDeEnsino = planoDeEnsino;
	}
	
	
}

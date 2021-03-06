package tcc.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Materia {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codMateria;
	
	@Column(name="nome", nullable=false)
	private String nome;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Horario> horarios;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="publico", nullable=false)
	private boolean publico;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Anotacao anotacoes;
	
	@JsonIgnore
	@OneToMany(mappedBy="materia", cascade=CascadeType.ALL)
	private List<Convite> convites;
	 
	@JsonIgnore
	@OneToMany
	private List<Tarefa> tarefas;
	
//	@JsonIgnore
	@OneToOne(cascade = CascadeType.PERSIST)
	private PlanoDeEnsino planoDeEnsino;
	
	public Materia() {
		super();
	}

	
	
	public Materia(String nome, List<Horario> horarios, String descricao, boolean publico, Anotacao anotacoes,
			List<Convite> convites, List<Tarefa> tarefas) {
		super();
		this.nome = nome;
		this.horarios = horarios;
		this.descricao = descricao;
		this.publico = publico;
		this.anotacoes = anotacoes;
		this.convites = convites;
		this.tarefas = tarefas;
	}



	public long getCodMateria() {
		return codMateria;
	}

	public void setCodMateria(long codMateria) {
		this.codMateria = codMateria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	public Anotacao getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(Anotacao anotacoes) {
		this.anotacoes = anotacoes;
	}

	public List<Convite> getConvites() {
		return convites;
	}

	public void setConvites(List<Convite> convites) {
		this.convites = convites;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}



	public PlanoDeEnsino getPlanoDeEnsino() {
		return planoDeEnsino;
	}



	public void setPlanoDeEnsino(PlanoDeEnsino planoDeEnsino) {
		this.planoDeEnsino = planoDeEnsino;
	}
	
	
}

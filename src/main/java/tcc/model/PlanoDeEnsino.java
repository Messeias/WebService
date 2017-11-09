package tcc.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PlanoDeEnsino {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codPlanoDeEnsino;
	
	@Column(name="professor", nullable=false)
	private String professor;
	

//	@OneToMany(mappedBy="ideaProfile", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Assunto> assuntos;
	
	@JsonIgnore
	@OneToOne(mappedBy="planoDeEnsino")
	private Materia materia;
	
	public PlanoDeEnsino() {
		super();
	}

	public PlanoDeEnsino(String professor, List<Assunto> assuntos) {
		super();
		this.professor = professor;
		this.assuntos = assuntos;
	}

	public long getCodPlanoDeEnsino() {
		return codPlanoDeEnsino;
	}

	public void setCodPlanoDeEnsino(long codPlanoDeEnsino) {
		this.codPlanoDeEnsino = codPlanoDeEnsino;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}
	
	
}

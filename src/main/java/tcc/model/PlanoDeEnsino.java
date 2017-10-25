package tcc.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PlanoDeEnsino {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codPlanoDeEnsino;
	
	@Column(name="professor", nullable=false)
	private String professor;
	
	@OneToMany(mappedBy="planoDeEnsino")
	private List<Assunto> assuntos;
	
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

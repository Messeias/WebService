package tcc.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Convite {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codConvite;
	
	@Column(name="status", nullable=false)
	private boolean status;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	private Usuario usuario;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	private Materia materia;
	
	public Convite() {
		super();
	}

	

	public Convite(boolean status, Usuario usuario, Materia materia) {
		super();
		this.status = status;
		this.usuario = usuario;
		this.materia = materia;
	}



	public long getCodConvite() {
		return codConvite;
	}

	public void setCodConvite(long codConvite) {
		this.codConvite = codConvite;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

package tcc.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Horario {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codHorario;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(style = "DDD/hh:mm")
	private Date hora;

	@ManyToOne
	private Materia materia;
	
	public Horario() {
		super();
	}

	public Horario(Date hora, Materia materia) {
		super();
		this.hora = hora;
		this.materia = materia;
	}

	public long getCodHorario() {
		return codHorario;
	}

	public void setCodHorario(long codHorario) {
		this.codHorario = codHorario;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	
}

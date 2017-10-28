package tk.vopros.backend.model.issue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import tk.vopros.backend.model.User;

@Entity
public class Issue {



	@Id @GeneratedValue public Long id;
	
	public String titulo;
	
	@Enumerated(EnumType.ORDINAL)
	public TipoIssue tipo;
	
	@Enumerated(EnumType.ORDINAL)
	public TipoGravedad gravedad;
	
	@Enumerated(EnumType.ORDINAL)
	public TipoPrioridad prioridad;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public User asignado;
	 
	public Issue() {}
	
	public Issue(String titulo) {
		this.titulo = titulo;
	}
	
	public Issue(String titulo, TipoIssue tipo,TipoGravedad gravedad, TipoPrioridad prioridad) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.gravedad = gravedad;
		this.prioridad = prioridad;
	}
	
	public Issue(String titulo, TipoIssue tipo,TipoGravedad gravedad, TipoPrioridad prioridad, User asignado) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.gravedad = gravedad;
		this.prioridad = prioridad;
		this.asignado = asignado;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoIssue getTipo() {
		return tipo;
	}

	public void setTipo(TipoIssue tipo) {
		this.tipo = tipo;
	}

	public TipoGravedad getGravedad() {
		return gravedad;
	}

	public void setGravedad(TipoGravedad gravedad) {
		this.gravedad = gravedad;
	}

	public TipoPrioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(TipoPrioridad prioridad) {
		this.prioridad = prioridad;
	}

	public User getAsignado() {
		return asignado;
	}

	public void setAsignado(User asignado) {
		this.asignado = asignado;
	}

}
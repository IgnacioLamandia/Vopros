package tk.vopros.backend.model.issue;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import tk.vopros.backend.model.Estado;
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
	
	@Enumerated(EnumType.ORDINAL)
	public Estado estado;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate expiracion;
	
	@ManyToOne(cascade = CascadeType.MERGE)
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
	
	public Issue(String titulo, TipoIssue tipo,TipoGravedad gravedad, TipoPrioridad prioridad, LocalDate expiracion, User asignado) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.gravedad = gravedad;
		this.prioridad = prioridad;
		this.estado = Estado.NUEVO;
		this.expiracion = expiracion;
		this.asignado = asignado;
	}
	
	public Issue(String titulo, TipoIssue tipo, TipoGravedad gravedad, TipoPrioridad prioridad, Estado estado,
			LocalDate expiracion, User asignado) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.gravedad = gravedad;
		this.prioridad = prioridad;
		this.estado = estado;
		this.expiracion = expiracion;
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

	public LocalDate getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(LocalDate expiracion) {
		this.expiracion = expiracion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
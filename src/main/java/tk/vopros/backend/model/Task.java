package tk.vopros.backend.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tk.vopros.backend.model.issue.JsonFormat;
import tk.vopros.backend.model.issue.TipoPrioridad;

@Entity
public class Task {
	
	@Id @GeneratedValue public Long id;
	public String nombre;
	public String descripcion;
	
	@Enumerated(EnumType.ORDINAL)
	public NivelDificultad dificultad;
	
	@Enumerated(EnumType.ORDINAL)
	public TipoPrioridad prioridad;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	public User asignado;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate expiracion;
	
	public Task(){
		
	}
	
	public Task(String nombre, String descripcion, NivelDificultad dificultad, TipoPrioridad prioridad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dificultad = dificultad;
		this.prioridad = prioridad;
	}
	
	public Task(String nombre, String descripcion, NivelDificultad dificultad, TipoPrioridad prioridad, User asignado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dificultad = dificultad;
		this.prioridad = prioridad;
		this.asignado = asignado;
	}
	
	public Task(String nombre, String descripcion, NivelDificultad dificultad, TipoPrioridad prioridad, User asignado, LocalDate expiracion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.dificultad = dificultad;
		this.prioridad = prioridad;
		this.asignado = asignado;
		this.expiracion = expiracion;
	}
	
	public Task(String nombre, String descripcion, User asignado) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.asignado = asignado;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoPrioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(TipoPrioridad prioridad) {
		this.prioridad = prioridad;
	}

	public NivelDificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(NivelDificultad dificultad) {
		this.dificultad = dificultad;
	}
	
	public User getAsignado(){
		return asignado;
	}
	
	public void setAsignado(User nuevoAsignado){
		this.asignado = nuevoAsignado;
	}
	
	public LocalDate getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(LocalDate expiracion) {
		this.expiracion = expiracion;
	}

}
package tk.vopros.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id @GeneratedValue public Long id;
	public String nombre;
	public String descripcion;
	@ManyToOne(cascade = CascadeType.ALL)
	public User asignado;
	
	
	public Task(){
		
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

}
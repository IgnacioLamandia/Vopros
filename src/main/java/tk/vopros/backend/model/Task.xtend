package tk.vopros.backend.model

import javax.persistence.Entity
import org.eclipse.xtend.lib.annotations.Accessors
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Entity
@Accessors
class Task {
	
	@Id @GeneratedValue private Long id;
	String nombre;
	String descripcion;
	
	new(){
		
	}
	
	new(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
}
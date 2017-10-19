package tk.vopros.backend.model

import javax.persistence.Entity
import org.eclipse.xtend.lib.annotations.Accessors
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Entity
@Accessors
class Issue {

	@Id @GeneratedValue private Long id;
	String nombre;
	
	new() {}
	
	new(String nombre) {
		this.nombre = nombre;
	}

}
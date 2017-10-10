package tk.vopros.backend.model

import javax.persistence.Entity
import org.eclipse.xtend.lib.annotations.Accessors
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@Accessors
class User {
	@Id @GeneratedValue private Long id;
	String nombre
	String apellido
	String email
	
	new() {}
	
	new(String unNombre, String unApellido, String unEmail){
		nombre = unNombre
		apellido = unApellido
		email = unEmail
	}
}
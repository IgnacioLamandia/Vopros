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
	String contrasenha
	
	new() {}
	
	new(String unNombre, String unApellido, String unEmail, String unaContrasenha){
		nombre = unNombre
		apellido = unApellido
		email = unEmail
		contrasenha = unaContrasenha
	}
	
	
	//Lo pense asi para que solo un usuario pueda crear un proyecto pero ni idea salu2
	def nuevoProyecto(String nombreProyecto){
		return new Proyecto(nombreProyecto, this);
	}
}
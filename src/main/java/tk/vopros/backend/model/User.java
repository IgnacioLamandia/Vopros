package tk.vopros.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User {
	@Id @GeneratedValue private Long id;
	public String nombre;
	public String apellido;
	public String email;
	public String contrasenha;
	
	public User() {}
	
	public User(String unNombre, String unApellido, String unEmail, String unaContrasenha){
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.email = unEmail;
		this.contrasenha = unaContrasenha;
	}
	
	
	//Lo pense asi para que solo un usuario pueda crear un proyecto pero ni idea salu2
	public Proyecto nuevoProyecto(String nombreProyecto){
		return new Proyecto(nombreProyecto, this);
	}
}
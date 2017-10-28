package tk.vopros.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	public Long id;
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
	
	
	public void nuevoProyecto(Proyecto proyect){
		proyect.miembros.add(this);
		proyect.setCreador(this);
	}
}
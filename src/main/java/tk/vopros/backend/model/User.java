package tk.vopros.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	public Long id;
	@Column(unique=true)
	public String usuario;
	public String nombre;
	public String apellido;
	public String email;
	public String password;
	@ElementCollection(fetch=FetchType.EAGER,targetClass=Long.class)
	public Set<Long> proyectos;
	
	public User() {}
	
	public User(String unUsuario,String unNombre, String unApellido, String unEmail, String unaContrasenha){
		this.usuario = unUsuario;
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.email = unEmail;
		this.password = unaContrasenha;
		this.proyectos = new HashSet<Long>();
	}
	
	
	public void nuevoProyecto(Proyecto proyect){
		proyect.miembros.add(this);
		proyect.setCreador(this);
	}
}
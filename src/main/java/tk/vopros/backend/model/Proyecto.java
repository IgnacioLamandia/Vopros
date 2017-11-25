package tk.vopros.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import javax.persistence.FetchType;
import tk.vopros.backend.model.issue.Issue;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Proyecto {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	public String nombre;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User creador;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	public List<User> miembros = new ArrayList<User>();
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public Set<Issue> issues = new HashSet<Issue>();
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	public Set<Task> tasks = new HashSet<Task>();
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public Set<Dibujo> dibujos = new HashSet<Dibujo>();
	
	public Proyecto(){
		
	}
	
	public Proyecto(String nombreProyecto) {
		this.nombre=nombreProyecto;
	}
	
	public Proyecto(String nombreProyecto , User creadorU){
		this.nombre = nombreProyecto;
		this.creador = creadorU;
		this.miembros.add(creadorU);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public User getCreador() {
		return creador;
	}

	public void setCreador(User creador) {
		this.creador = creador;
	}

	public List<User> getMiembros() {
		return miembros;
	}

	public void setMiembros(List<User> miembros) {
		this.miembros = miembros;
	}

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
}
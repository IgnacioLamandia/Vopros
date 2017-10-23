package tk.vopros.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
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
	@Id @GeneratedValue private Long id;
	public String nombre;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User creador;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public List<User> miembros = new ArrayList<User>();
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	public Set<Issue> issues = new HashSet<Issue>();
	
	public Proyecto(){
		
	}
	
	public Proyecto(String nombreProyecto , User creadorU){
		this.nombre = nombreProyecto;
		this.creador = creadorU;
		this.miembros.add(creadorU);
	}
	
}
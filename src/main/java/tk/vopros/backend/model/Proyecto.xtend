package tk.vopros.backend.model

import javax.persistence.Entity
import org.eclipse.xtend.lib.annotations.Accessors
import javax.persistence.Id
import javax.persistence.GeneratedValue
import java.util.List
import javax.persistence.ManyToOne
import javax.persistence.CascadeType
import javax.persistence.OneToMany
import java.util.ArrayList
import javax.persistence.FetchType
import tk.vopros.backend.model.issue.Issue

@Entity
@Accessors
class Proyecto {
	@Id @GeneratedValue private Long id;
	String nombre;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User creador;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<User> miembros = new ArrayList<User>();
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<Issue> issues = new ArrayList<Issue>();
	
	new (){
		
	}
	
	new(String nombreProyecto , User creadorU){
		nombre = nombreProyecto;
		creador = creadorU;
		miembros.add(creadorU);
	}
	
}
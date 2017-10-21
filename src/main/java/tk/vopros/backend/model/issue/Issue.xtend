package tk.vopros.backend.model.issue

import javax.persistence.Entity
import org.eclipse.xtend.lib.annotations.Accessors
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.ManyToOne
import javax.persistence.CascadeType
import tk.vopros.backend.model.User

@Entity
@Accessors
class Issue {

	@Id @GeneratedValue private Long id;
	
	String titulo
	
	@Enumerated(EnumType.ORDINAL)
	TipoIssue tipo
	
	@Enumerated(EnumType.ORDINAL)
	TipoGravedad gravedad;
	
	@Enumerated(EnumType.ORDINAL)
	TipoPrioridad prioridad;
	
	@ManyToOne(cascade = CascadeType.ALL)
	User asignado
	 
	new() {}
	
	new(String titulo) {
		this.titulo = titulo
	}
	
	new(String titulo, TipoIssue tipo,TipoGravedad gravedad, TipoPrioridad prioridad) {
		this.titulo = titulo
		this.tipo = tipo
		this.gravedad = gravedad
		this.prioridad = prioridad
	}
	
	new(String titulo, TipoIssue tipo,TipoGravedad gravedad, TipoPrioridad prioridad, User asignado) {
		this.titulo = titulo
		this.tipo = tipo
		this.gravedad = gravedad
		this.prioridad = prioridad
		this.asignado = asignado
	}

}
package tk.vopros.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Dibujo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public String name;
	
	@Lob
	public String imagen;
	
	public Dibujo(){
		
	}
	
	public Dibujo(String nombre,String dibujo){
		name = nombre;
		imagen = dibujo;
	}
	
	
}
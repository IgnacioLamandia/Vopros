package tk.vopros.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje {
	@Id @GeneratedValue 
	public Long id;
	
	@ManyToOne()
	private User emisor;
	
	@ManyToOne()
	private User receptor;
	
	private String texto;
	
	public Mensaje(){
		
	}
	
	public Mensaje(User emisor, User receptor, String texto){
		this.emisor = emisor;
		this.receptor = receptor;
		this.texto = texto;
	}

	public User getEmisor() {
		return emisor;
	}

	public User getReceptor() {
		return receptor;
	}

	public String getTexto() {
		return texto;
	}
}


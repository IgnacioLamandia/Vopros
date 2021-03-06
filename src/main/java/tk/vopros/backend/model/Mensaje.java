package tk.vopros.backend.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje {
	
	@Id @GeneratedValue 
	public Long id;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private User emisor;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private User receptor;
	
	private String texto;
	
	private long orderCreacion;
	
	private static long intOrder = 0;
	
	
	public Mensaje(){
		
	}
	
	public Mensaje(User emisor, User receptor, String texto){
		this.emisor = emisor;
		this.receptor = receptor;
		this.texto = texto;
		this.orderCreacion = intOrder;
		intOrder++;
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
	
	public void setEmisor(User emisor) {
		this.emisor = emisor;
	}

	public void setReceptor(User receptor) {
		this.receptor = receptor;
	}
}


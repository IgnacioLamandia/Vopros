package tk.vopros.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateConversacionDAO;
import tk.vopros.backend.model.Conversacion;
import tk.vopros.backend.model.Mensaje;

@Service("chatService")
public class ChatService {
	
	@Autowired
	private HibernateConversacionDAO conversacionDAO;
	@Autowired
	
	public ChatService(){
		this.conversacionDAO = new HibernateConversacionDAO();
	}
	
	@Transactional
	public Conversacion getConversacionById(long id){
		return conversacionDAO.getById(id);
	}
	
	@Transactional
	public void setConversacion(Conversacion conversacion){
		conversacionDAO.save(conversacion);
	}
	
	@Transactional
	public void addMensaje(long idConver, Mensaje mensaje){
		Conversacion conver = conversacionDAO.getById(idConver);
		conver.addMensaje(mensaje);		
		conversacionDAO.update(conver);		
	}

}


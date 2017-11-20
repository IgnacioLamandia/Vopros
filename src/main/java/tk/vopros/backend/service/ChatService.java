package tk.vopros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateMensajeDAO;
import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.Mensaje;
import tk.vopros.backend.model.User;

@Service("chatService")
public class ChatService {
	
	@Autowired
	private HibernateMensajeDAO mensajeDAO;
	private HibernateUserDAO userDAO;
	@Autowired
	
	public ChatService(){
		this.mensajeDAO = new HibernateMensajeDAO();
		this.userDAO = new HibernateUserDAO();
	}
	
	@Transactional
	public void setMensaje(User emisor, User receptor, Mensaje mensaje){
		mensaje.setEmisor(emisor);
		mensaje.setReceptor(receptor);
		mensajeDAO.save(mensaje);
	}
	
	@Transactional
	public List<Mensaje> getConversacion(String emisorUsername, String receptorUsername) {
		return mensajeDAO.getMensajes(emisorUsername,receptorUsername);		
	}

}


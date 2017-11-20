package tk.vopros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateMensajeDAO;
import tk.vopros.backend.model.Mensaje;

@Service("chatService")
public class ChatService {
	
	@Autowired
	private HibernateMensajeDAO mensajeDAO;
	@Autowired
	
	public ChatService(){
		this.mensajeDAO = new HibernateMensajeDAO();
	}
	
	@Transactional
	public void setMensaje(Mensaje mensaje){
		mensajeDAO.save(mensaje);
	}
	
	@Transactional
	public List<Mensaje> getConversacion(String emisorUsername, String receptorUsername) {
		return mensajeDAO.getMensajes(emisorUsername,receptorUsername);		
	}

}


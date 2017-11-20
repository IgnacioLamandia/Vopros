package tk.vopros.backend.service;

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

}


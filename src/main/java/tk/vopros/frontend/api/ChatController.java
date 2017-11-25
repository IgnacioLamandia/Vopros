package tk.vopros.frontend.api;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import tk.vopros.backend.model.Mensaje;
import tk.vopros.backend.model.User;
import tk.vopros.backend.service.ChatService;
import tk.vopros.backend.service.UserService;

@RestController
public class ChatController {
	
	@Autowired
	ChatService chatService = new ChatService();
	
	@Autowired
	UserService userService = new UserService();
	
	@RequestMapping(value = "/conversacion/{emisorUsername}/{receptorUsername}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Mensaje>> getTask(@PathVariable("emisorUsername") String emisorUsername, @PathVariable("receptorUsername") String receptorUsername) {

		try{
			User emisor	= this.userService.getByUsername(emisorUsername);
			User receptor = this.userService.getByUsername(receptorUsername);
			
			List<Mensaje> conversacion = chatService.getConversacion(emisor.usuario,receptor.usuario);
			return new ResponseEntity<List<Mensaje>>(conversacion,HttpStatus.OK);
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Mensaje>>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Mensaje>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }
	
	@RequestMapping(value = "/mensaje", method = RequestMethod.POST, consumes = "application/json")	
	public ResponseEntity<Void> nuevoProyecto(@RequestBody Mensaje mensaje){
		
		try {
			User emisor	= this.userService.getByUsername(mensaje.getEmisor().usuario);
			User receptor = this.userService.getByUsername(mensaje.getReceptor().usuario);
			
			chatService.setMensaje(emisor, receptor, mensaje);;
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}catch (NoResultException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}


package tk.vopros.frontend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.vopros.backend.model.Conversacion;
import tk.vopros.backend.model.Mensaje;
import tk.vopros.backend.service.ChatService;

@RestController
public class ChatController {
	
	@Autowired
	ChatService chatService = new ChatService();
	
	@RequestMapping(value = "/conversacion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Conversacion> getTask(@PathVariable("id") Long id) {
        System.out.println("Fetching Conversacion with id " + id);
        Conversacion conversacion = this.chatService.getConversacionById(id);
        if (conversacion == null) {
            System.out.println("Conversacion with id " + id + " not found");
            return new ResponseEntity<Conversacion>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Conversacion>(conversacion, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/conversacion/{converId}/mensaje", method = RequestMethod.PUT, consumes = "application/json")	
	public ResponseEntity<Void> nuevoProyecto(@PathVariable("converId") long converId,@RequestBody Mensaje mensaje){
		System.out.println("Updating Conversacion with id " + converId);
		Conversacion conver = chatService.getConversacionById(converId);
		if(conver == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			conver.addMensaje(mensaje);
			chatService.addMensaje(converId, mensaje);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}


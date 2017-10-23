package tk.vopros.frontend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.vopros.backend.model.Proyecto;
import tk.vopros.backend.model.User;
import tk.vopros.backend.service.ProyectoService;
import tk.vopros.backend.service.UserService;

@RestController
public class ProjectController {
	
	@Autowired
	ProyectoService proyectService = new ProyectoService();
	
	@Autowired
	UserService userService= new UserService();
	
	
	public ProjectController() {
	}
    
    
	@RequestMapping(value = "/proyectos", method = RequestMethod.GET, produces = "application/json")   
	public ResponseEntity<List<Proyecto>> getProyectos() {
       	return new ResponseEntity<List<Proyecto>>(this.proyectService.getAll(),HttpStatus.OK);
    }


	@RequestMapping(value = "/proyecto/{userId}", method = RequestMethod.POST, consumes = "application/json")	
	public ResponseEntity<Void> nuevoProyecto(@PathVariable("userId") long id,@RequestBody Proyecto proyecto){
		User user = userService.getById(id);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
		else {
			this.proyectService.setProyecto(user.nuevoProyecto(proyecto.nombre));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
}

}

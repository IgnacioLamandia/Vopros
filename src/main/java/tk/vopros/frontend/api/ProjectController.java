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
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
		else {
			proyecto.setCreador(user);
			proyecto.miembros.add(user);
			long idP=this.proyectService.setProyecto(proyecto);
			user.proyectos.add(idP);
			userService.updateUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
}
	
	
	@RequestMapping(value = "/proyecto/{id}", method = RequestMethod.PUT, consumes = "application/json")	
	public ResponseEntity<Void> actualizarProyecto(@PathVariable("id") long id,@RequestBody Proyecto proyecto){
		Proyecto proyect = proyectService.getById(id);
		if(proyect == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}else {
			proyect.nombre = proyecto.nombre;
			proyect.issues = proyecto.issues;
			proyect.tasks = proyecto.tasks;
			proyect.miembros = proyecto.miembros;
			this.proyectService.updateProyecto(proyect);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}
	
	
	@RequestMapping(value = "/proyecto/{id}", method = RequestMethod.GET, produces = "application/json")	
	public ResponseEntity<Proyecto> getProyecto(@PathVariable("id") long id){
		Proyecto proyect=this.proyectService.getById(id);
		if(proyect == null) {
			return new ResponseEntity<Proyecto>(HttpStatus.NOT_FOUND);

		}
		else {
			return new ResponseEntity<Proyecto>(proyect,HttpStatus.OK);

		}
	}
	
	@RequestMapping(value = "/proyecto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Proyecto> deleteIssue(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Proyecto with id " + id);
 
        Proyecto proyect = proyectService.getById(id);
        if (proyect == null) {
            System.out.println("Unable to delete. Proyecto with id " + id + " not found");
            return new ResponseEntity<Proyecto>(HttpStatus.NOT_FOUND);
        }
 
        proyectService.delete(id);
        return new ResponseEntity<Proyecto>(HttpStatus.NO_CONTENT);
    }

}

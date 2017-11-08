package tk.vopros.frontend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.vopros.backend.model.Task;
import tk.vopros.backend.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	TaskService taskService = new TaskService();
	
	
	
	 @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Task>> getTask() {
	         List<Task> task = this.taskService.getAll();
	        if (task == null) {
	            System.out.println("no Tasks found");
	            return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<Task>>(task, HttpStatus.OK);
	    }
	
	 @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
	        System.out.println("Fetching User with id " + id);
	         Task task = this.taskService.getById(id);
	        if (task == null) {
	            System.out.println("Task with id " + id + " not found");
	            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Task>(task, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/task", method = RequestMethod.POST, consumes = "application/json")
	    public void postTask(@RequestBody Task input) {
	         Task task = new Task(input.nombre,input.descripcion,input.dificultad,input.prioridad,input.estado, input.asignado,input.expiracion);
	         this.taskService.save(task);
	    }
	 
	    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Task> deleteTask(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting Issue with id " + id);
	 
	        Task task = taskService.getById(id);
	        if (task == null) {
	            System.out.println("Unable to delete. Task with id " + id + " not found");
	            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	        }
	 
	        taskService.delete(id);
	        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	    }
	    
	    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task ){
	        System.out.println("Updating Task " + id);
	         
	        Task currentTask = taskService.getById(id);
	         
	        if (currentTask==null) {
	            System.out.println("Task with id " + id + " not found");
	            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentTask.setNombre(task.nombre);
	        currentTask.setDescripcion(task.descripcion);
	        currentTask.setDificultad(task.dificultad);
	        currentTask.setPrioridad(task.prioridad);
	        currentTask.setEstado(task.estado);
	        currentTask.setAsignado(task.asignado);
	        currentTask.setExpiracion(task.expiracion);
	         
	        taskService.update(currentTask);
	        return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
	    }
	 

}

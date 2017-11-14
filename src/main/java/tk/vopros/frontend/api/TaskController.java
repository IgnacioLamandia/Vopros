package tk.vopros.frontend.api;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.vopros.backend.model.Proyecto;
import tk.vopros.backend.model.Task;
import tk.vopros.backend.service.ProyectoService;
import tk.vopros.backend.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	TaskService taskService = new TaskService();
	
	@Autowired
	ProyectoService proyectoService = new ProyectoService();
	
	@Autowired
	JavaMailSender sender;
	
	
	
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
	 
	 @RequestMapping(value = "/task/{idProyecto}", method = RequestMethod.POST, consumes = "application/json")
	    public ResponseEntity<Void> postTask(@RequestBody Task input,@PathVariable("idProyecto") long idProy) throws Exception{
		 	Proyecto proyecto=this.proyectoService.getById(idProy);
		 	if(proyecto == null) {
		 		return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		 	}
		 	else {
				 Task task = new Task(input.nombre,input.descripcion,input.dificultad,input.prioridad,input.estado, input.asignado,input.expiracion);
		         this.taskService.save(task);
		         
		         proyecto.tasks.add(task);
		         this.proyectoService.updateProyecto(proyecto);
		         try {
			         notificarIntegrantes(proyecto,"Nueva tarea agregada a "+proyecto.nombre,"La tarea " +task.nombre+ " ha sido agregada a su proyecto.");

		         }
		         catch(Exception e) {
		        	 e.printStackTrace();
		         }
		         return new ResponseEntity<Void>(HttpStatus.OK);

		 	}

	         


	    }
	 
	    @RequestMapping(value = "/task/{id}/{idProyecto}", method = RequestMethod.DELETE)
	    public ResponseEntity<Task> deleteTask(@PathVariable("id") long id,@PathVariable("idProyecto") long idProy) {
	        System.out.println("Fetching & Deleting Issue with id " + id);
	 
	        Task task = taskService.getById(id);
	        Proyecto proyecto = proyectoService.getById(idProy);
	        if (task == null || proyecto == null) {
	            System.out.println("Unable to delete. Task with id " + id + " not found");
	            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	        }
	        Task reference = null;
	        for(Task t:proyecto.tasks) {
	        	if(t.id == id) {
	        		reference = t;
	        	}
	        };
    		proyecto.tasks.remove(reference);

	        proyectoService.updateProyecto(proyecto);
	         try {
				notificarIntegrantes(proyecto,"Tarea eliminada en proyecto  "+proyecto.nombre,"La tarea " +task.nombre+ " ha sido borrada de su proyecto.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        
	        //	        taskService.delete(id);
	        return new ResponseEntity<Task>(HttpStatus.OK);
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
	 
	    
	    private void notificarIntegrantes(Proyecto proyecto,String asunto,String contenido)throws Exception {
	         MimeMessage msg = sender.createMimeMessage();
	         MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true); 
	         String[] tos = new String[proyecto.miembros.size()];;
	         for(int i=0;i<proyecto.miembros.size();i++) {
	        	 tos[i]=proyecto.miembros.get(i).email;
	         };
	         msgHelper.setTo(tos);
	         msgHelper.setText(contenido);
	         msgHelper.setSubject(asunto);
	         sender.send(msg);
	    }

}

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

import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.service.IssueService;

@RestController
public class IssueController {

	@Autowired
	IssueService issueService = new IssueService();
	
	
	
	 @RequestMapping(value = "/issues", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Issue>> getIssue() {
	         List<Issue> issue = this.issueService.getAll();
	        if (issue == null) {
	            System.out.println("no Issues found");
	            return new ResponseEntity<List<Issue>>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<List<Issue>>(issue, HttpStatus.OK);
	    }
	
	 @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Issue> getIssue(@PathVariable("id") Long id) {
	        System.out.println("Fetching User with id " + id);
	         Issue issue = this.issueService.getById(id);
	        if (issue == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/issue", method = RequestMethod.POST, consumes = "application/json")
	    public void postIssue(@RequestBody Issue input) {
	         Issue issue = new Issue(input.titulo,input.tipo,input.gravedad,input.prioridad,input.expiracion,input.asignado);
	         this.issueService.save(issue);
	    }
	 
	    @RequestMapping(value = "/issue/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Issue> deleteIssue(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting Issue with id " + id);
	 
	        Issue user = issueService.getById(id);
	        if (user == null) {
	            System.out.println("Unable to delete. Issue with id " + id + " not found");
	            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
	        }
	 
	        issueService.delete(id);
	        return new ResponseEntity<Issue>(HttpStatus.NO_CONTENT);
	    }
	    
	    @RequestMapping(value = "/issue/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Issue> updateIssue(@PathVariable("id") long id, @RequestBody Issue issue ){
	        System.out.println("Updating User " + id);
	         
	        Issue currentIssue = issueService.getById(id);
	         
	        if (currentIssue==null) {
	            System.out.println("Issue with id " + id + " not found");
	            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentIssue.setTitulo(issue.titulo);
	        currentIssue.setTipo(issue.tipo);
	        currentIssue.setGravedad(issue.gravedad);
	        currentIssue.setPrioridad(issue.prioridad);
	        currentIssue.setExpiracion(issue.expiracion);
	        currentIssue.setAsignado(issue.asignado);
	         
	        issueService.update(currentIssue);
	        return new ResponseEntity<Issue>(currentIssue, HttpStatus.OK);
	    }
	 

}

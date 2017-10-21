package tk.vopros.frontend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.service.IssueService;

@RestController
class IssueC {

	@Autowired
	IssueService issueService = new IssueService();
	
	
	 @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Issue> getUser(@PathVariable("id") Long id) {
	        System.out.println("Fetching User with id " + id);
	         Issue issue = this.issueService.getById(id);
	        if (issue == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Issue>(issue, HttpStatus.OK);
	    }
	 

}

package tk.vopros.frontend.api
/*
import org.hibernate.sql.Delete
import org.springframework.http.StreamingHttpOutputMessage.Body
import tk.vopros.backend.model.issue.Issue
import tk.vopros.backend.service.IssueService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired

@RestController
class IssuesController {

	@Autowired
	IssueService issueService = new IssueService;
	
	new(){}
/*
	@Post("/issue")
	def createIssue(@Body String body) {
		response.contentType = "application/json"
		try {
			var Issue issue = body.fromJson(typeof(Issue))
			this.issueService.save(issue)
			ok()
		} catch (UnrecognizedPropertyException exception) {
			badRequest('{ "error": "El body debe ser un issue" }')
		}
	}

	@Get("/issues")
	def getIssues() {
		response.contentType = "application/json"
		ok(this.issueService.getAll().toJson)
	}
	

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Issue> getUser(@PathVariable("id") Long id) {
        System.out.println("Fetching User with id " + id);
         var issue = this.issueService.getById(id)
        if (issue == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Issue>(issue, HttpStatus.OK);
    }
 
    @Delete('/issue/:id')
    def deleteIssueById() {
        response.contentType = "application/json"
        try {
            this.issueService.delete(Long.valueOf(id))
            ok()
        }
        catch (NumberFormatException ex) {
        	badRequest('{ "error": "El id debe ser un numero" }')
        }
    }

}
*/
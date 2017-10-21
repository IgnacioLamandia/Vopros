package tk.vopros.frontend.api

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.uqbar.xtrest.api.annotation.Body
import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.json.JSONUtils
import tk.vopros.backend.model.issue.Issue
import tk.vopros.backend.service.IssueService
import org.uqbar.xtrest.api.annotation.Delete

@Controller
class IssuesController {

	extension JSONUtils = new JSONUtils;
	IssueService issueService = new IssueService;
	
	new(){}

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
	
	@Get("/issue/:id")
    def getIssueById() {
        response.contentType = "application/json"
        try {        	
            var issue = this.issueService.getById(Long.valueOf(id))
            if (issue == null) {
            	notFound('{ "error": "No existe issue con ese id" }')
            } else {
            	ok(issue.toJson)
            }
        }
        catch (NumberFormatException ex) {
        	badRequest('{ "error": "El id debe ser un numero" }')
        }
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

package tk.vopros.frontend.api

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Body
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import tk.vopros.backend.model.Issue
import tk.vopros.backend.appmodel.VoprosAppModel
import org.uqbar.xtrest.api.XTRest
import tk.vopros.backend.service.HibernateDataService

@Controller
public class IssuesController {
    extension JSONUtils = new JSONUtils
	VoprosAppModel appModel;
	
	
	
	new () {
		this.appModel = new VoprosAppModel();
		var dataService = new HibernateDataService();
		dataService.createDatosIniciales();
	}
    
    @Post("/issue")
    def createIssue(@Body String body) {
        response.contentType = "application/json"
        try {
	        var Issue issue = body.fromJson(typeof(Issue))	
	        this.appModel.setIssue(issue)
	    	ok()
        } catch (UnrecognizedPropertyException exception) {
        	badRequest('{ "error": "El body debe ser un Issue" }')        	
        } 
    }
    
    @Get("/issues")
    def getIssues() {
        response.contentType = "application/json"
       	ok(this.appModel.getAllIssues().toJson)
    }
    
   	def static void main(String[] args) {
		XTRest.start(9000,IssuesController);
	}
}
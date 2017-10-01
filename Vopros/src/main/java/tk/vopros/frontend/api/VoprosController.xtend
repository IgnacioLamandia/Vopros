package tk.vopros.frontend.api

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Body
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import tk.vopros.backend.model.Issue
import tk.vopros.backend.appmodel.VoprosAppModel

@Controller
public class VoprosController {
    extension JSONUtils = new JSONUtils
	VoprosAppModel appModel;
	
	new (VoprosAppModel appModel) {
		this.appModel = appModel;
	}
	
    @Get("/saludo")
    def saludar() {
        response.contentType = "application/json"
       	ok(this.appModel.helloWorld().toJson)
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
}
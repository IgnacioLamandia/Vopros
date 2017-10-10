package tk.vopros.frontend.api

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.json.JSONUtils
import tk.vopros.backend.service.HibernateDataService
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Body
import org.uqbar.xtrest.api.annotation.Get
import tk.vopros.backend.service.UserService

@Controller
class UsersController extends VoprosController{
	extension JSONUtils = new JSONUtils;
	UserService userService = new UserService();
	
	
	
	new () {
	}
    
    @Get("/users")
    def getUsers() {
        response.contentType = "application/json"
       	ok(this.userService.getAll().toJson)
    }

}
package tk.vopros.frontend.api

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.uqbar.xtrest.api.annotation.Body
import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.json.JSONUtils
import tk.vopros.backend.model.User
import tk.vopros.backend.service.UserService

@Controller
class UsersController {
	extension JSONUtils = new JSONUtils;
	UserService userService = new UserService();

	new() {
	}

	@Get("/users")
	def getUsers() {
		response.contentType = "application/json"
		ok(this.userService.getAll().toJson)
	}

	@Post("/users")
	def createUser(@Body String body) {
		response.contentType = "application/json"
		try {
			var User user = body.fromJson(typeof(User))
			this.userService.setUser(user)
			ok()
		} catch (UnrecognizedPropertyException exception) {
			badRequest('{ "error": "El body debe ser un User" }')
		}
	}

}

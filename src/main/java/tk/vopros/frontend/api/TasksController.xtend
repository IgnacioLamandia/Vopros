package tk.vopros.frontend.api

import org.uqbar.xtrest.api.annotation.Controller
import tk.vopros.backend.service.TaskService
import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.annotation.Body
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Get
import tk.vopros.backend.model.Task
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException

@Controller
class TasksController {
	extension JSONUtils = new JSONUtils;
	TaskService taskService = new TaskService;
	
	new(){}

	@Post("/task")
	def createIssue(@Body String body) {
		response.contentType = "application/json"
		try {
			var Task task = body.fromJson(typeof(Task))
			this.taskService.save(task)
			ok()
		} catch (UnrecognizedPropertyException exception) {
			badRequest('{ "error": "El body debe ser un Task" }')
		}
	}

	@Get("/tasks")
	def getIssues() {
		response.contentType = "application/json"
		ok(this.taskService.getAll().toJson)
	}
	
}
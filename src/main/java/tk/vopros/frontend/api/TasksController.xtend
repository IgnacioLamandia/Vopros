package tk.vopros.frontend.api

import org.uqbar.xtrest.api.annotation.Controller
import tk.vopros.backend.service.TaskService
import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.annotation.Body
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Get
import tk.vopros.backend.model.Task
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.uqbar.xtrest.api.annotation.Delete

@Controller
class TasksController {
	extension JSONUtils = new JSONUtils;
	TaskService taskService = new TaskService;

	new() {
	}

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

	@Get("/task/:id")
	def getIssueById() {
		response.contentType = "application/json"
		try {
			var task = this.taskService.getById(Long.valueOf(id))
			if (task == null) {
				notFound('{ "error": "No existe task con ese id" }')
			} else {
				ok(task.toJson)
			}
		} catch (NumberFormatException ex) {
			badRequest('{ "error": "El id debe ser un numero" }')
		}
	}

	@Delete('/task/:id')
	def deleteIssueById() {
		response.contentType = "application/json"
		try {
			this.taskService.delete(Long.valueOf(id))
			ok()
		} catch (NumberFormatException ex) {
			badRequest('{ "error": "El id debe ser un numero" }')
		}
	}

}

package tk.vopros.frontend.api

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.json.JSONUtils
import tk.vopros.backend.service.ProyectoService
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Body


@Controller
class ProyectsController{
	extension JSONUtils = new JSONUtils;
	ProyectoService proyectService = new ProyectoService();
	
	
	
	new () {
	}
    
    
    //Por ahora devuelve todos, luego debe devolver solo los de x usuario
    @Get("/proyectos")
    def getProyectos() {
        response.contentType = "application/json"
       	ok(this.proyectService.getAll().toJson)
    }


	@Post("/proyecto/:userID")
	def nuevoProyecto(@Body String bodyFromJSON){
		response.contentType = "application/json"
		
		// parse int buscar usuario y hacer que cree proyecto
		
		return badRequest('{"error":"No implementado"')
	}
}
package ToolsStudios.Vopros.Service.Controller

import ToolsStudios.Vopros.Domain.VoprosAppModel
import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.json.JSONUtils

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
}
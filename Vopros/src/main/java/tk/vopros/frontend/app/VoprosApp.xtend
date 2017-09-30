package ToolsStudios.Vopros.Service.App

import ToolsStudios.Vopros.Domain.VoprosAppModel
import org.uqbar.xtrest.api.XTRest
import ToolsStudios.Vopros.Service.Controller.VoprosController

class VoprosApp {
	
	 def static void main( String[] args )
    {
        var appModel = new VoprosAppModel();
        var controller = new VoprosController(appModel);
        XTRest.startInstance(controller, 9000);
    }
}
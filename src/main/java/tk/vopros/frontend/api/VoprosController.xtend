package tk.vopros.frontend.api

import org.uqbar.xtrest.api.XTRest
import org.uqbar.xtrest.api.annotation.Controller
import tk.vopros.backend.service.HibernateDataService

@Controller
public class VoprosController {

	new() {
		var dataService = new HibernateDataService();
		dataService.createDatosIniciales();
	}

	def static void main(String[] args) {
		XTRest.start(9000, VoprosController, 
							UsersController, 
							ProyectsController, 
							IssuesController, 
							TasksController, 
							LoginController);
	}
}

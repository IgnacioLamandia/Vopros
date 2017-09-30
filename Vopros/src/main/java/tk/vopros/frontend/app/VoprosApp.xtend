package tk.vopros.frontend.app

import org.uqbar.xtrest.api.XTRest
import tk.vopros.backend.appmodel.VoprosAppModel
import tk.vopros.backend.service.HibernateDataService
import tk.vopros.frontend.api.VoprosController

class VoprosApp {

	def static void main(String[] args) {
		var appModel = new VoprosAppModel();
		var dataService = new HibernateDataService();
		dataService.createDatosIniciales();
		var controller = new VoprosController(appModel);
		XTRest.startInstance(controller, 9000);
	}
}
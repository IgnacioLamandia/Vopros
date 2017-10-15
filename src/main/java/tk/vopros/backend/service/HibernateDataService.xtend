package tk.vopros.backend.service

import tk.vopros.backend.dao.HibernateIssueDAO
import tk.vopros.backend.dao.HibernateProyectoDAO
import tk.vopros.backend.dao.HibernateUserDAO
import tk.vopros.backend.model.Issue
import tk.vopros.backend.model.User

class HibernateDataService {
	
	var issueDAO = new HibernateIssueDAO();
	var userDAO = new HibernateUserDAO();
	var proyectDAO = new HibernateProyectoDAO();
	
	def createDatosIniciales() {

		issueDAO.save(new Issue("Issue 1"))
		issueDAO.save(new Issue("Issue 2"))
		issueDAO.save(new Issue("Issue 3"))
		issueDAO.save(new Issue("Issue 4"))
		issueDAO.save(new Issue("Issue 5"))
		issueDAO.save(new Issue("Issue 6"))
		issueDAO.save(new Issue("Issue 7"))
		
		var nachoL = new User("Nacho", "Lamandia", "nacho.lamandia@gmail.com")
		var gaston = new User("Gaston", "Veliez", "gaston.veliez@gmail.com")
		userDAO.save(nachoL)
		userDAO.save(gaston)		
		userDAO.save(new User("Matias", "Cavallin", "matias.cavallin@gmail.com"))
		userDAO.save(new User("Ignacio", "Gioya", "ignacio.gioya@gmail.com"))
		
		var proyecto = nachoL.nuevoProyecto("Vopros")
		proyecto.miembros.add(gaston)
		proyectDAO.save(proyecto)
	
	}
	
}
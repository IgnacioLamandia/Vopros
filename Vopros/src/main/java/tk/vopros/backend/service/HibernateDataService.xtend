package tk.vopros.backend.service

import tk.vopros.backend.model.Issue
import tk.vopros.backend.dao.HibernateIssueDAO
import tk.vopros.backend.model.User
import tk.vopros.backend.dao.HibernateUserDAO

class HibernateDataService {
	
	var issueDAO = new HibernateIssueDAO();
	var userDAO = new HibernateUserDAO();
	
	def createDatosIniciales() {

		issueDAO.saveIssue(new Issue("Issue 1"))
		issueDAO.saveIssue(new Issue("Issue 2"))
		issueDAO.saveIssue(new Issue("Issue 3"))
		issueDAO.saveIssue(new Issue("Issue 4"))
		issueDAO.saveIssue(new Issue("Issue 5"))
		issueDAO.saveIssue(new Issue("Issue 6"))
		issueDAO.saveIssue(new Issue("Issue 7"))
		
		userDAO.saveUser(new User("Nacho", "Lamandia", "nacho.lamandia@gmail.com"))
		userDAO.saveUser(new User("Gaston", "Veliez", "gaston.veliez@gmail.com"))		
		userDAO.saveUser(new User("Matias", "Cavallin", "matias.cavallin@gmail.com"))
		userDAO.saveUser(new User("Ignacio", "Gioya", "ignacio.gioya@gmail.com"))
		
	}
	
}
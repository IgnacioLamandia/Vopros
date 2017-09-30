package tk.vopros.backend.service

import tk.vopros.backend.model.Issue
import tk.vopros.backend.dao.HibernateIssueDAO

class HibernateDataService {
	
	var issueDAO = new HibernateIssueDAO();
	
	def createDatosIniciales() {

		issueDAO.saveIssue(new Issue("Issue 1"))
		issueDAO.saveIssue(new Issue("Issue 2"))
		issueDAO.saveIssue(new Issue("Issue 3"))
		issueDAO.saveIssue(new Issue("Issue 4"))
		issueDAO.saveIssue(new Issue("Issue 5"))
		issueDAO.saveIssue(new Issue("Issue 6"))
		issueDAO.saveIssue(new Issue("Issue 7"))
		
	}
	
}
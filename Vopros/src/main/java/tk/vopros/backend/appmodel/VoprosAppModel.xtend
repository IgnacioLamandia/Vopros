package tk.vopros.backend.appmodel

import org.eclipse.xtend.lib.annotations.Accessors
import tk.vopros.backend.model.Issue
import tk.vopros.backend.dao.IssueDAO
import tk.vopros.backend.dao.HibernateIssueDAO

@Accessors
class VoprosAppModel {

	var IssueDAO issueDAO = new HibernateIssueDAO

	def setIssue(Issue issue) {
		issueDAO.saveIssue(issue)
	}

	def helloWorld() {
		return "Hello World!";
	}
	
	def getAllIssues() {
		issueDAO.getAllIssue
	}
	
}

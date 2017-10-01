package tk.vopros.backend.appmodel

import tk.vopros.backend.dao.HibernateIssueDAO
import tk.vopros.backend.dao.IssueDAO
import tk.vopros.backend.model.Issue

class VoprosAppModel {

	var IssueDAO issueDAO = new HibernateIssueDAO

	def setIssue(Issue issue) {
		issueDAO.saveIssue(issue)
	}

	def getAllIssues() {
		issueDAO.getAllIssue
	}

}

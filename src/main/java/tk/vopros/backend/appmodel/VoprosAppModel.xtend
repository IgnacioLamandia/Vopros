package tk.vopros.backend.appmodel

import tk.vopros.backend.dao.HibernateIssueDAO
import tk.vopros.backend.model.Issue

class VoprosAppModel {

	var HibernateIssueDAO issueDAO = new HibernateIssueDAO

	def setIssue(Issue issue) {
		issueDAO.save(issue)
	}

	def getAllIssues() {
		issueDAO.getAll
	}

}

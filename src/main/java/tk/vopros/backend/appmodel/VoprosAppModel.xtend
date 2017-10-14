package tk.vopros.backend.appmodel

import tk.vopros.backend.dao.HibernateIssueDAO
import tk.vopros.backend.model.Issue

class VoprosAppModel {

	var HibernateIssueDAO issueDAO = new HibernateIssueDAO

	def save(Issue issue) {
		issueDAO.save(issue)
	}
	
	def update(Issue issue) {
		issueDAO.update(issue)
	}
	
	def delete(Issue issue) {
		issueDAO.delete(issue)
	}

	def getById(Long id) {
		issueDAO.getById(id)
	}

	def getAll() {
		issueDAO.getAll
	}

}

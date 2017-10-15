package tk.vopros.backend.service

import tk.vopros.backend.dao.HibernateIssueDAO
import tk.vopros.backend.model.Issue

class IssueService {

	var HibernateIssueDAO issueDAO

	new() {
		issueDAO = new HibernateIssueDAO
	}

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

package tk.vopros.backend.dao

import tk.vopros.backend.model.Issue

class HibernateIssueDAO extends GenericDAO<Issue> {

	new() {
		super(Issue)
	}
}

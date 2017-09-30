package tk.vopros.backend.dao

import tk.vopros.backend.model.Issue

interface IssueDAO {
	
	def void saveIssue(Issue issue);
	
}
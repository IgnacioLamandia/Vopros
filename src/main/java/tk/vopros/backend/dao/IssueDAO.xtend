package tk.vopros.backend.dao

import tk.vopros.backend.model.Issue
import java.util.List

interface IssueDAO {
	
	def void saveIssue(Issue issue);
	
	def List<Issue> getAllIssue();
	
}
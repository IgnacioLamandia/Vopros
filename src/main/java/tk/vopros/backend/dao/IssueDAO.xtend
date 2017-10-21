package tk.vopros.backend.dao

import tk.vopros.backend.model.issue.Issue
import java.util.List

interface IssueDAO {
	
	def void saveIssue(Issue issue);
	
	def List<Issue> getAllIssue();
	
}
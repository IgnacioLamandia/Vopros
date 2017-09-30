package tk.vopros.backend.appmodel

import org.eclipse.xtend.lib.annotations.Accessors
import tk.vopros.backend.model.Issue
import tk.vopros.backend.dao.IssueDAO
import tk.vopros.backend.dao.HibernateIssueDAO

@Accessors
class VoprosAppModel {

	var IssueDAO issues = new HibernateIssueDAO

	def setIssue(Issue issue) {
		issues.saveIssue(issue)
	}

	def helloWorld() {
		return "Hello World!";
	}
}

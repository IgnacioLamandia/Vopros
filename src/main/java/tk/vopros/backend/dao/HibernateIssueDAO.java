package tk.vopros.backend.dao;

import org.springframework.stereotype.Repository;

import tk.vopros.backend.model.issue.Issue;

@Repository
public class HibernateIssueDAO extends GenericDAO<Issue> {

	public HibernateIssueDAO() {
		super(Issue.class);
	}
}

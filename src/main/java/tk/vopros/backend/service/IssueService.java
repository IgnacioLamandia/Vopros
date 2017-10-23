package tk.vopros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateIssueDAO;
import tk.vopros.backend.model.issue.Issue;


@Service("issueService")
public class IssueService {

	@Autowired
	private HibernateIssueDAO issueDAO;

	public IssueService() {
		issueDAO = new HibernateIssueDAO();
	}

	@Transactional
	public void save(Issue issue) {
		issueDAO.save(issue);
	}
	
	@Transactional
	public void update(Issue issue) {
		issueDAO.update(issue);
	}
	
	@Transactional
	public void delete(Long id) {
		issueDAO.delete(this.getById(id));
	}

	@Transactional
	public Issue getById(Long id) {
		return issueDAO.getById(id);
	}

	@Transactional
	public List<Issue> getAll() {
		return issueDAO.getAll();
	}

}

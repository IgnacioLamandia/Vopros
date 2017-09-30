package tk.vopros.backend.dao

import org.hibernate.HibernateException
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tk.vopros.backend.model.Issue
import tk.vopros.backend.dao.IssueDAO


class HibernateIssueDAO implements IssueDAO {

	private static final SessionFactory sessionFactory = new Configuration()
	.configure()
	.addAnnotatedClass(Issue).
	buildSessionFactory()

	override saveIssue(Issue issue) {

		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			session.save(issue);
			session.getTransaction.commit
		} catch (HibernateException e) {
			session.getTransaction.rollback
			throw new RuntimeException(e)
		} finally {
			session.close
		}
	}	
}

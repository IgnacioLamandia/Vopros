package tk.vopros.backend.dao

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tk.vopros.backend.model.User
import org.hibernate.HibernateException
import javax.persistence.Query

class HibernateUserDAO implements UserDAO{
	private static final SessionFactory sessionFactory = new Configuration()
	.configure()
	.addAnnotatedClass(User).
	buildSessionFactory()

	override saveUser(User user) {

		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			session.save(user);
			session.getTransaction.commit
		} catch (HibernateException e) {
			session.getTransaction.rollback
			throw new RuntimeException(e)
		} finally {
			session.close
		}
	}
	
	override getAllUser() {
		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			val String hql = "from User ";
			val Query query = session.createQuery(hql, User);
			session.getTransaction.commit
			return query.getResultList();
		} catch (HibernateException e) {
			session.getTransaction.rollback
			throw new RuntimeException(e)
		} finally {
			session.close
		}
	}
}
package tk.vopros.backend.dao

import org.hibernate.HibernateException
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

class GenericDAO<T> {

	private Class<T> entityType;
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()

	new(Class<T> entityType) {
		this.entityType = entityType;
	}

	def save(T object) {
		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			session.save(object)
			session.getTransaction.commit
		} catch (HibernateException e) {
			session.getTransaction.rollback
			throw new RuntimeException(e)
		} finally {
			session.close
		}
	}

	def getAll() {
		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			val hql = "from " + entityType.getSimpleName();
			val query = session.createQuery(hql, entityType);
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

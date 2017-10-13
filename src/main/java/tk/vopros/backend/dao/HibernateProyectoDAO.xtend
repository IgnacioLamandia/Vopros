package tk.vopros.backend.dao

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tk.vopros.backend.model.Proyecto
import org.hibernate.HibernateException
import javax.persistence.Query

class HibernateProyectoDAO implements ProyectoDAO{
	private static final SessionFactory sessionFactory = new Configuration()
	.configure()
	.addAnnotatedClass(Proyecto).
	buildSessionFactory()

	override saveProyecto(Proyecto proyecto) {

		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			session.save(proyecto);
			session.getTransaction.commit
		} catch (HibernateException e) {
			session.getTransaction.rollback
			throw new RuntimeException(e)
		} finally {
			session.close
		}
	}
	
	override getAllProyectos() {
		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			val String hql = "from Proyecto ";
			val Query query = session.createQuery(hql, Proyecto);
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
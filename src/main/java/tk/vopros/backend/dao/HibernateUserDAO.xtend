package tk.vopros.backend.dao

import tk.vopros.backend.model.User
import org.hibernate.HibernateException

class HibernateUserDAO extends GenericDAO<User> {

	new() {
		super(User)
	}
	
	def Boolean validate(User user){
		val session = sessionFactory.openSession
		try {
			session.beginTransaction
			val hql = "from " + User.getSimpleName() + 
						" u " + "where u.nombre = :unNombre " 
							  +   "and u.contrasenha = :unaContrasenha";
			val query = session.createQuery(hql, User);
			query.setParameter("unNombre", user.nombre);
			query.setParameter("unaContrasenha", user.contrasenha);
			return !query.list.isEmpty;
		} catch (HibernateException e) {
			session.getTransaction.rollback
			throw new RuntimeException(e)
		} finally {
			session.close
		}
	
	}
}

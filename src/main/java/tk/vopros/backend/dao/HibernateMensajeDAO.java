package tk.vopros.backend.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tk.vopros.backend.model.Mensaje;
import tk.vopros.backend.model.User;

@Repository
public class HibernateMensajeDAO extends GenericDAO<Mensaje> {
	public HibernateMensajeDAO() {
		super(Mensaje.class);
	}
	
	@SuppressWarnings("deprecation")
	public List<Mensaje> getMensajes(String emisorUsername, String receptorUsername) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from " + "Mensaje " + 
						    "m " + "where (m.emisor.usuario = :emisorUsername AND m.receptor.usuario = :receptorUsername) "
								 + "   OR (m.emisor.usuario = :receptorUsername AND m.receptor.usuario = :emisorUsername) "
								 + "ORDER BY m.id DESc";
			Query<Mensaje> query = session.createQuery(hql, Mensaje.class);
			query.setParameter("emisorUsername", emisorUsername);
			query.setParameter("receptorUsername", receptorUsername);
			return query.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
		
	}
}

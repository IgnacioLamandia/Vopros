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
	public List<Mensaje> getMensajes(Long emisorId, Long receptorId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from " + "Mensaje " + 
						    "m " + "where (m.emisor.id = :emisorId AND m.receptor.id = :receptorId) "
								 + "   OR (m.emisor.id = :receptorId AND m.receptor.id = :emisorId)";
			Query<Mensaje> query = session.createQuery(hql, Mensaje.class);
			query.setParameter("emisorId", emisorId);
			query.setParameter("receptorId", receptorId);
			return query.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
		
	}
}

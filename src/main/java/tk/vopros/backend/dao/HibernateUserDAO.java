package tk.vopros.backend.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.HibernateException;


import tk.vopros.backend.model.User;

@Repository
public class HibernateUserDAO extends GenericDAO<User>{
	
	public HibernateUserDAO() {
		super(User.class);
	}
	
	@SuppressWarnings("deprecation")
	public Boolean validate(User user){
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from " + "User" + 
						" u " + "where u.nombre = :unNombre " 
							  +   "and u.contrasenha = :unaContrasenha";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("unNombre", user.nombre);
			query.setParameter("unaContrasenha", user.contrasenha);
			return !query.list().isEmpty();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	
	}
	
	@SuppressWarnings("deprecation")
	public List<User> searchUsers(String nombreBuscado){
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from " + "User" + 
						" u " + "where u.nombre like :unNombre";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("unNombre", nombreBuscado);
			return query.list();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}



}

package tk.vopros.backend.dao;

import org.springframework.stereotype.Repository;

import tk.vopros.backend.model.Conversacion;

@Repository
public class HibernateConversacionDAO extends GenericDAO<Conversacion> {
	public HibernateConversacionDAO() {
		super(Conversacion.class);
	}
}

package tk.vopros.backend.dao;

import org.springframework.stereotype.Repository;

import tk.vopros.backend.model.Mensaje;

@Repository
public class HibernateMensajeDAO extends GenericDAO<Mensaje> {
	public HibernateMensajeDAO() {
		super(Mensaje.class);
	}
}

package tk.vopros.backend.dao;

import tk.vopros.backend.model.Proyecto;

public class HibernateProyectoDAO extends GenericDAO<Proyecto>{
	
	public HibernateProyectoDAO() {
		super(Proyecto.class);
	}

}

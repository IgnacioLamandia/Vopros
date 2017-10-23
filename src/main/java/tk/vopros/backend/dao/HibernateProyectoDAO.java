package tk.vopros.backend.dao;

import org.springframework.stereotype.Repository;

import tk.vopros.backend.model.Proyecto;

@Repository
public class HibernateProyectoDAO extends GenericDAO<Proyecto>{
	
	public HibernateProyectoDAO() {
		super(Proyecto.class);
	}

}

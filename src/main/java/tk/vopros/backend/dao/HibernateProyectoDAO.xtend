package tk.vopros.backend.dao

import tk.vopros.backend.model.Proyecto

class HibernateProyectoDAO extends GenericDAO<Proyecto> {

	new() {
		super(Proyecto)
	}
}

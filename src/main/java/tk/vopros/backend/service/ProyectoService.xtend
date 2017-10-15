package tk.vopros.backend.service

import tk.vopros.backend.dao.HibernateProyectoDAO
import tk.vopros.backend.model.Proyecto

class ProyectoService {
	HibernateProyectoDAO proyectoDAO;
	new (){
		proyectoDAO = new HibernateProyectoDAO()
	}
	
	def getAll(){
		return proyectoDAO.getAll
	}
	
	def setProyecto(Proyecto proyectoNuevo){
		proyectoDAO.save(proyectoNuevo)
	}
}
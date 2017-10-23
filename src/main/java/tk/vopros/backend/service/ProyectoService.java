package tk.vopros.backend.service;

import java.util.List;

import tk.vopros.backend.dao.HibernateProyectoDAO;
import tk.vopros.backend.model.Proyecto;

public class ProyectoService {
	private HibernateProyectoDAO proyectoDAO;
	public ProyectoService(){
		proyectoDAO = new HibernateProyectoDAO();
	}
	
	public List<Proyecto> getAll(){
		return proyectoDAO.getAll();
	}
	
	public void setProyecto(Proyecto proyectoNuevo){
		proyectoDAO.save(proyectoNuevo);
	}
}
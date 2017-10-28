package tk.vopros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateProyectoDAO;
import tk.vopros.backend.model.Proyecto;

@Service
public class ProyectoService {
	
	@Autowired
	private HibernateProyectoDAO proyectoDAO;
	
	public ProyectoService(){
		proyectoDAO = new HibernateProyectoDAO();
	}
	
	@Transactional
	public List<Proyecto> getAll(){
		return proyectoDAO.getAll();
	}
	@Transactional
	public void setProyecto(Proyecto proyectoNuevo){
		proyectoDAO.save(proyectoNuevo);
	}

	public void updateProyecto(Proyecto proyect) {
		// TODO Auto-generated method stub
		
	}

	public Proyecto getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
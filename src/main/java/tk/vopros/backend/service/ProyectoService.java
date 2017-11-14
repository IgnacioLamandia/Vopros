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
	public long setProyecto(Proyecto proyectoNuevo){
		return proyectoDAO.save(proyectoNuevo);
	}

	@Transactional
	public void updateProyecto(Proyecto proyect) {
		proyectoDAO.update(proyect);
		
	}

	public Proyecto getById(long id) {
		return proyectoDAO.getById(id);
	}
	
	@Transactional
	public void delete(Long id) {
		proyectoDAO.delete(this.getById(id));
	}

}
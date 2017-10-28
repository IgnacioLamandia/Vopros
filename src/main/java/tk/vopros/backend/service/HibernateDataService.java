package tk.vopros.backend.service;

import tk.vopros.backend.dao.HibernateIssueDAO;
import tk.vopros.backend.dao.HibernateProyectoDAO;
import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.model.Proyecto;
import tk.vopros.backend.model.User;
import tk.vopros.backend.model.issue.TipoIssue;
import tk.vopros.backend.model.issue.TipoGravedad;
import tk.vopros.backend.model.issue.TipoPrioridad;

public class HibernateDataService {
	
	HibernateIssueDAO issueDAO = new HibernateIssueDAO();
	HibernateUserDAO userDAO = new HibernateUserDAO();
	HibernateProyectoDAO proyectDAO = new HibernateProyectoDAO();
	
	public void createDatosIniciales() {

		issueDAO.save(new Issue("Issue 1", TipoIssue.BUG, TipoGravedad.CRITICO, TipoPrioridad.ALTA));
		issueDAO.save(new Issue("Issue 2", TipoIssue.BUG, TipoGravedad.GRAVE, TipoPrioridad.ALTA));
		issueDAO.save(new Issue("Issue 3", TipoIssue.MEJORA, TipoGravedad.GRAVE, TipoPrioridad.ALTA));
		issueDAO.save(new Issue("Issue 4", TipoIssue.MEJORA, TipoGravedad.MENOR, TipoPrioridad.MEDIA));
		issueDAO.save(new Issue("Issue 5", TipoIssue.BUG, TipoGravedad.REGULAR, TipoPrioridad.MEDIA));
		issueDAO.save(new Issue("Issue 6", TipoIssue.BUG, TipoGravedad.MENOR, TipoPrioridad.BAJA));
		issueDAO.save(new Issue("Issue 7", TipoIssue.PREGUNTA, TipoGravedad.CRITICO, TipoPrioridad.ALTA));
		
		User nachoL = new User("Nacho", "Lamandia", "nacho.lamandia@gmail.com","123");
		User gaston = new User("Gaston", "Veliez", "gaston.veliez@gmail.com","123");
		userDAO.save(nachoL);
		userDAO.save(gaston);		
		userDAO.save(new User("Matias", "Cavallin", "matias.cavallin@gmail.com","123"));
		userDAO.save(new User("Ignacio", "Gioya", "ignacio.gioya@gmail.com","123"));
		
		Proyecto proyecto = new Proyecto("Vopros");
		nachoL.nuevoProyecto(proyecto);
		proyecto.miembros.add(gaston);
		proyectDAO.save(proyecto);
	
	}
	
}
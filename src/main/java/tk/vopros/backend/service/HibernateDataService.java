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
		Issue issue1 = new Issue("Issue 1", TipoIssue.BUG, TipoGravedad.CRITICO, TipoPrioridad.ALTA);
		Issue issue2 = new Issue("Issue 2", TipoIssue.BUG, TipoGravedad.GRAVE, TipoPrioridad.ALTA);
		Issue issue3 = new Issue("Issue 3", TipoIssue.MEJORA, TipoGravedad.GRAVE, TipoPrioridad.ALTA);
		Issue issue4 = new Issue("Issue 4", TipoIssue.MEJORA, TipoGravedad.MENOR, TipoPrioridad.MEDIA);
		Issue issue5 = new Issue("Issue 5", TipoIssue.BUG, TipoGravedad.REGULAR, TipoPrioridad.MEDIA);
		Issue issue6 = new Issue("Issue 6", TipoIssue.BUG, TipoGravedad.MENOR, TipoPrioridad.BAJA);
		Issue issue7 = new Issue("Issue 7", TipoIssue.PREGUNTA, TipoGravedad.CRITICO, TipoPrioridad.ALTA);
		
		issueDAO.save(issue1);
		issueDAO.save(issue2);
		issueDAO.save(issue3);
		issueDAO.save(issue4);
		issueDAO.save(issue5);
		issueDAO.save(issue6);
		issueDAO.save(issue7);
		
		User nachoL = new User("Nacho", "Lamandia", "nacho.lamandia@gmail.com","123");
		User gaston = new User("Gaston", "Veliez", "gaston.veliez@gmail.com","123");
		userDAO.save(nachoL);
		userDAO.save(gaston);		
		userDAO.save(new User("Matias", "Cavallin", "matias.cavallin@gmail.com","123"));
		userDAO.save(new User("Ignacio", "Gioya", "ignacio.gioya@gmail.com","123"));
		
		Proyecto proyecto = new Proyecto("Vopros");
		nachoL.nuevoProyecto(proyecto);
		proyecto.miembros.add(gaston);
		proyecto.issues.addAll(issueDAO.getAll());
		proyectDAO.save(proyecto);
	
	}
	
}
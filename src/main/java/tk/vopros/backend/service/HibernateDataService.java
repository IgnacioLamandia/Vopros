package tk.vopros.backend.service;

import java.time.LocalDate;

import tk.vopros.backend.dao.HibernateIssueDAO;
import tk.vopros.backend.dao.HibernateMensajeDAO;
import tk.vopros.backend.dao.HibernateProyectoDAO;
import tk.vopros.backend.dao.HibernateTaskDAO;
import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.model.Mensaje;
import tk.vopros.backend.model.NivelDificultad;
import tk.vopros.backend.model.Proyecto;
import tk.vopros.backend.model.Task;
import tk.vopros.backend.model.User;
import tk.vopros.backend.model.issue.TipoIssue;
import tk.vopros.backend.model.issue.TipoGravedad;
import tk.vopros.backend.model.issue.TipoPrioridad;

public class HibernateDataService {
	
	HibernateUserDAO userDAO = new HibernateUserDAO();
	HibernateIssueDAO issueDAO = new HibernateIssueDAO();
	HibernateTaskDAO taskDAO = new HibernateTaskDAO();
	HibernateProyectoDAO proyectDAO = new HibernateProyectoDAO();
	HibernateMensajeDAO mensajeDAO = new HibernateMensajeDAO();
	
	public void createDatosIniciales() {
		
		User ignacioL = new User("Hammer99","Ignacio", "Lamandia", "ignaciolamandia@gmail.com","123");
		User gaston = new User("Driller99","Gaston", "Veliez", "gastonveliez95@gmail.com","123");
		User matias = new User("Aczero","Matias", "Cavallin", "matiascavallin96@gmail.com","123");
		User ignacioG = new User("Ioja","Ignacio", "Gioya", "ignacio.gioya@gmail.com","123");
		userDAO.save(ignacioL);
		userDAO.save(gaston);
		userDAO.save(ignacioG);
		userDAO.save(matias);
		
		Issue issue1 = new Issue("Issue 1", TipoIssue.BUG, TipoGravedad.CRITICO, TipoPrioridad.ALTA, LocalDate.of(2018,2,2), gaston);
		Issue issue2 = new Issue("Issue 2", TipoIssue.BUG, TipoGravedad.GRAVE, TipoPrioridad.ALTA, LocalDate.now(), matias);
		Issue issue3 = new Issue("Issue 3", TipoIssue.MEJORA, TipoGravedad.GRAVE, TipoPrioridad.ALTA, LocalDate.of(2017,12,15), ignacioG);
		Issue issue4 = new Issue("Issue 4", TipoIssue.MEJORA, TipoGravedad.MENOR, TipoPrioridad.MEDIA, LocalDate.now(), ignacioL);
		Issue issue5 = new Issue("Issue 5", TipoIssue.BUG, TipoGravedad.REGULAR, TipoPrioridad.MEDIA, LocalDate.of(2018,12,12), gaston);
		Issue issue6 = new Issue("Issue 6", TipoIssue.BUG, TipoGravedad.MENOR, TipoPrioridad.BAJA, LocalDate.now(), matias);
		Issue issue7 = new Issue("Issue 7", TipoIssue.PREGUNTA, TipoGravedad.CRITICO, TipoPrioridad.ALTA, LocalDate.of(2018,12,1), ignacioL);
		
		issueDAO.save(issue1);
		issueDAO.save(issue2);
		issueDAO.save(issue3);
		issueDAO.save(issue4);
		issueDAO.save(issue5);
		issueDAO.save(issue6);
		issueDAO.save(issue7);
		
		Task task1 = new Task("Task 1", "Descripcion 1", NivelDificultad.XXL, TipoPrioridad.ALTA, gaston, LocalDate.of(2019,5,5));
		Task task2 = new Task("Task 2", "Descripcion 2", NivelDificultad.XL, TipoPrioridad.ALTA, matias, LocalDate.now());
		Task task3 = new Task("Task 3", "Descripcion 3", NivelDificultad.S, TipoPrioridad.BAJA, ignacioG, LocalDate.of(2017,12,25));
		Task task4 = new Task("Task 4", "Descripcion 4", NivelDificultad.L, TipoPrioridad.MEDIA, ignacioL, LocalDate.now());
		
		taskDAO.save(task1);
		taskDAO.save(task2);
		taskDAO.save(task3);
		taskDAO.save(task4);	
		
		Proyecto proyecto = new Proyecto("Vopros");
		ignacioL.nuevoProyecto(proyecto);
		proyecto.miembros.add(gaston);
		proyecto.miembros.add(matias);
		proyecto.miembros.add(ignacioG);
		proyecto.issues.addAll(issueDAO.getAll());
		proyecto.tasks.addAll(taskDAO.getAll());
		long idP =proyectDAO.save(proyecto);
		System.out.println(idP);
		gaston.proyectos.add(idP);
		matias.proyectos.add(idP);
		ignacioG.proyectos.add(idP);
		ignacioL.proyectos.add(idP);
		userDAO.update(gaston);
		userDAO.update(matias);
		userDAO.update(ignacioG);
		userDAO.update(ignacioL);
		
		Mensaje mensaje1 = new Mensaje(gaston,ignacioG,"Nachoo");
		Mensaje mensaje2 = new Mensaje(ignacioG,gaston,"Que onda prro,todo bien?"); 
		Mensaje mensaje3 = new Mensaje(gaston,ignacioG,"Todeo bien");
		
		mensajeDAO.save(mensaje1);
		mensajeDAO.save(mensaje2);
		mensajeDAO.save(mensaje3);
	
	}
	
}
package tk.vopros.backend.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import antlr.collections.List;
import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.model.issue.TipoGravedad;
import tk.vopros.backend.model.issue.TipoIssue;
import tk.vopros.backend.model.issue.TipoPrioridad;

public class ProyectoTestCase {

	public Proyecto proyecto;
	public User user;
	public User miembro;
	public Issue issue1;
	public Issue issue2;
	public Task task1;
	public Task	task2;
	
	@Before
	public void setUp() throws Exception {
		
		proyecto = new Proyecto("Vopros");
		user = new User("unUsuario","unNombre","unApellido","unEmail","unaContrasenha");
		user.nuevoProyecto(proyecto);
		issue1 = new Issue("Issue 1 ptc", TipoIssue.BUG, TipoGravedad.CRITICO, TipoPrioridad.ALTA,Estado.NUEVO,LocalDate.of(2018, 2, 2), null);
		task1 = new Task("Task 1 ptc", "Descripcion 1 db", NivelDificultad.XXL, TipoPrioridad.ALTA, null, LocalDate.of(2019,5,5));
		miembro = new User("Miembro","unNombreM","unApellidoM","unEmailM","unaContrasenhaM");
		issue2 = new Issue("Issue 2 db", TipoIssue.BUG, TipoGravedad.GRAVE, TipoPrioridad.ALTA, Estado.NUEVO,LocalDate.now(), null);
		task2 = new Task("Task 2 db", "Descripcion 2 db", NivelDificultad.XL, TipoPrioridad.ALTA, null, LocalDate.now());

	}

	@Test
	public void testGetYSetNombre() {
		assertEquals(proyecto.getNombre(), "Vopros");
		
		proyecto.setNombre("Nuevo nombre");
		assertEquals(proyecto.getNombre(), "Nuevo nombre");
	}
	
	@Test
	public void testGetYSetCreador() {
		assertEquals(proyecto.getCreador(), user);
		
		User user2 = new User();
		proyecto.setCreador(user2);
		assertEquals(proyecto.getCreador(), user2);
	}
	
	@Test
	public void testGetYAddMiembros() {
		ArrayList<User> miembrosIniciales = new ArrayList<User>();
		miembrosIniciales.add(user);
		proyecto.setMiembros(miembrosIniciales);
		assertEquals(proyecto.getMiembros(), miembrosIniciales);
		proyecto.miembros.add(miembro);
		assertTrue(proyecto.getMiembros().contains(miembro));
	}
	
	@Test
	public void testGetYAddIssues() {
		HashSet<Issue> issuesIniciales = new HashSet<Issue>();
		issuesIniciales.add(issue1);
		proyecto.setIssues(issuesIniciales);
		assertEquals(proyecto.getIssues(), issuesIniciales);
		proyecto.issues.add(issue2);
		assertTrue(proyecto.getIssues().contains(issue2));
	}
	
	@Test
	public void testGetYAddTasks() {
		HashSet<Task> tasksIniciales = new HashSet<Task>();
		tasksIniciales.add(task1);
		proyecto.setTasks(tasksIniciales);
		assertEquals(proyecto.getTasks(), tasksIniciales);
		proyecto.tasks.add(task2);
		assertTrue(proyecto.getTasks().contains(task2));
	}
	

}

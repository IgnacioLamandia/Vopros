package tk.vopros.backend.db;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.dao.HibernateTaskDAO;
import tk.vopros.backend.model.NivelDificultad;
import tk.vopros.backend.model.Task;
import tk.vopros.backend.model.issue.TipoPrioridad;

public class TaskDAOTestCase {
	public HibernateTaskDAO dao = new HibernateTaskDAO();
	public Task task1;
	public Task task2;
	public Task task3;

	@Before
	public void setUp() throws Exception {
		task1 = new Task("Task 1 db", "Descripcion 1 db", NivelDificultad.XXL, TipoPrioridad.ALTA, null, LocalDate.of(2019,5,5));
		task2 = new Task("Task 2 db", "Descripcion 2 db", NivelDificultad.XL, TipoPrioridad.ALTA, null, LocalDate.now());
		task3 = new Task("Task 3 db", "Descripcion 3 db", NivelDificultad.S, TipoPrioridad.BAJA, null, LocalDate.of(2017,12,25));}

	@Test
	public void testGuardarYRecuperarUnTask() {
		this.dao.save(this.task1);
		Long id = task1.id;
		Task recuperado = this.dao.getById(id);
		assertEquals(this.task1.id, (recuperado.id));
		
		this.dao.delete(task1);
	}

	@Test
	public void testActualizarUnTask() {
		this.dao.save(this.task1);
		assertEquals(this.task1.getPrioridad(), (TipoPrioridad.ALTA));
		task1.setPrioridad(TipoPrioridad.BAJA);
		this.dao.update(task1);
		
		Long id = task1.id;
		Task recuperado = this.dao.getById(id);
		assertEquals(recuperado.getPrioridad(), (TipoPrioridad.BAJA));
		
		this.dao.delete(task1);
	}

	@Test
	public void testGetAllTask() {
		this.dao.save(this.task1);
		this.dao.save(this.task2);
		this.dao.save(this.task3);
		ArrayList<Task> tasks = (ArrayList<Task>) this.dao.getAll();
		Integer size = (Integer) tasks.size();
		Assert.assertTrue(size.equals(3));
		
		this.dao.delete(task1);
		this.dao.delete(task2);
		this.dao.delete(task3);
	}

	@Test
	public void testBorrarUnTask() {
		this.dao.save(this.task1);
		this.dao.delete(task1);
		ArrayList<Task> tasks = (ArrayList<Task>) this.dao.getAll();
		Integer size = (Integer) tasks.size();
		Assert.assertTrue(size.equals(0)); //Es cero porque fue borrado el task
	}
}


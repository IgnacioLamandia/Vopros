package tk.vopros.backend.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.model.issue.TipoPrioridad;

public class TaskTestCase {
	
	public User user;
	public Task task;

	@Before
	public void setUp() throws Exception {
		
		user = new User();
		task = new Task("Task","Descripcion",NivelDificultad.XXL,TipoPrioridad.ALTA,Estado.EN_PROGRESO,user,LocalDate.of(2019,5,5));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetYSetNombre() {
		assertEquals(task.getNombre(), "Task");
		
		task.setNombre("Nuevo nombre");
		assertEquals(task.getNombre(), "Nuevo nombre");
	}
	
	@Test
	public void testGetYSetDescripcion() {
		assertEquals(task.getDescripcion(), "Descripcion");
		
		task.setDescripcion("Nueva descripcion");
		assertEquals(task.getDescripcion(), "Nueva descripcion");
	}
	
	@Test
	public void testGetYSetNivelDeDificultad() {
		assertEquals(task.getDificultad(), NivelDificultad.XXL);
		
		task.setDificultad(NivelDificultad.L);
		assertEquals(task.getDificultad(), NivelDificultad.L);
	}
	
	@Test
	public void testGetYSetTipoPrioridad() {
		assertEquals(task.getPrioridad(), TipoPrioridad.ALTA);
		
		task.setPrioridad(TipoPrioridad.BAJA);
		assertEquals(task.getPrioridad(), TipoPrioridad.BAJA);
	}
	
	@Test
	public void testGetYSetEstado() {
		assertEquals(task.getEstado(), Estado.EN_PROGRESO);
		
		task.setEstado(Estado.CERRADO);
		assertEquals(task.getEstado(), Estado.CERRADO);
	}
	
	@Test
	public void testGetYSetAsignado() {
		assertEquals(task.getAsignado(), user);
		
		User user2 = new User();
		task.setAsignado(user2);
		assertEquals(task.getAsignado(), user2);
	}
	
	@Test
	public void testGetYSetExpiracion() {
		assertEquals(task.getExpiracion(), LocalDate.of(2019,5,5));
		
		task.setExpiracion(LocalDate.of(2018,1,1));
		assertEquals(task.getExpiracion(), LocalDate.of(2018,1,1));
	}
}

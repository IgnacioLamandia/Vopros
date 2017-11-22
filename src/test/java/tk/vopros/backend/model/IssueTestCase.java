package tk.vopros.backend.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.model.issue.TipoGravedad;
import tk.vopros.backend.model.issue.TipoIssue;
import tk.vopros.backend.model.issue.TipoPrioridad;

public class IssueTestCase {

	public User user;
	public Issue issue;

	@Before
	public void setUp() throws Exception {
		
		user = new User();
		issue = new Issue("Issue",TipoIssue.BUG,TipoGravedad.GRAVE,TipoPrioridad.ALTA,Estado.EN_PROGRESO,LocalDate.of(2019,5,5),user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetYSetTitulo() {
		assertEquals(issue.getTitulo(), "Issue");
		
		issue.setTitulo("Nuevo nombre");
		assertEquals(issue.getTitulo(), "Nuevo nombre");
	}
	
	@Test
	public void testGetYSetTipo() {
		assertEquals(issue.getTipo(), TipoIssue.BUG);
		
		issue.setTipo(TipoIssue.PREGUNTA);
		assertEquals(issue.getTipo(), TipoIssue.PREGUNTA);
	}
	
	@Test
	public void testGetYSetTipoPrioridad() {
		assertEquals(issue.getPrioridad(), TipoPrioridad.ALTA);
		
		issue.setPrioridad(TipoPrioridad.BAJA);
		assertEquals(issue.getPrioridad(), TipoPrioridad.BAJA);
	}
	
	@Test
	public void testGetYSetEstado() {
		assertEquals(issue.getEstado(), Estado.EN_PROGRESO);
		
		issue.setEstado(Estado.CERRADO);
		assertEquals(issue.getEstado(), Estado.CERRADO);
	}
	
	@Test
	public void testGetYSetAsignado() {
		assertEquals(issue.getAsignado(), user);
		
		User user2 = new User();
		issue.setAsignado(user2);
		assertEquals(issue.getAsignado(), user2);
	}
	
	@Test
	public void testGetYSetExpiracion() {
		assertEquals(issue.getExpiracion(), LocalDate.of(2019,5,5));
		
		issue.setExpiracion(LocalDate.of(2018,1,1));
		assertEquals(issue.getExpiracion(), LocalDate.of(2018,1,1));
	}

}

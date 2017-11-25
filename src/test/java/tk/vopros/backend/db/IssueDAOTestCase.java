package tk.vopros.backend.db;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.dao.HibernateIssueDAO;
import tk.vopros.backend.model.Estado;
import tk.vopros.backend.model.issue.Issue;
import tk.vopros.backend.model.issue.TipoGravedad;
import tk.vopros.backend.model.issue.TipoIssue;
import tk.vopros.backend.model.issue.TipoPrioridad;

public class IssueDAOTestCase {

	public HibernateIssueDAO dao = new HibernateIssueDAO();
	public Issue issue1;
	public Issue issue2;
	public Issue issue3;

	@Before
	public void setUp() throws Exception {
		issue1 = new Issue("Issue 1 db", TipoIssue.BUG, TipoGravedad.CRITICO, TipoPrioridad.ALTA,Estado.NUEVO,
							LocalDate.of(2018, 2, 2), null);
		issue2 = new Issue("Issue 2 db", TipoIssue.BUG, TipoGravedad.GRAVE, TipoPrioridad.ALTA, Estado.NUEVO,
							LocalDate.now(), null);
		issue3 = new Issue("Issue 3 db", TipoIssue.MEJORA, TipoGravedad.GRAVE, TipoPrioridad.ALTA,Estado.NUEVO,
							LocalDate.of(2017, 12, 15), null);
	}

	@Test
	public void testGuardarYRecuperarUnIssue() {
		this.dao.save(this.issue1);
		Long id = issue1.id;
		Issue recuperado = this.dao.getById(id);
		assertEquals(this.issue1.id, (recuperado.id));
	
		this.dao.delete(issue1);
	}

	@Test
	public void testActualizarUnIssue() {
		this.dao.save(this.issue1);
		assertEquals(this.issue1.getEstado(), (Estado.NUEVO));
		issue1.setEstado(Estado.CERRADO);
		this.dao.update(issue1);
		Long id = issue1.id;
		Issue recuperado = this.dao.getById(id);
		assertEquals(recuperado.getEstado(), (Estado.CERRADO));
		
		this.dao.delete(issue1);
	}

	@Test
	public void testGetAllIssues() {
		this.dao.save(this.issue1);
		this.dao.save(this.issue2);
		this.dao.save(this.issue3);
		ArrayList<Issue> issues = (ArrayList<Issue>) this.dao.getAll();
		Integer size = (Integer) issues.size();
		Assert.assertTrue(size.equals(3));
		
		this.dao.delete(issue1);
		this.dao.delete(issue2);
		this.dao.delete(issue3);

	}

	@Test
	public void testBorrarUnIssue() {
		this.dao.save(this.issue1);
		this.dao.delete(issue1);
		ArrayList<Issue> issues = (ArrayList<Issue>) this.dao.getAll();
		Integer size = (Integer) issues.size();
		Assert.assertTrue(size.equals(0));
	}
}

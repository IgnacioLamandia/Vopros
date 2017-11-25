package tk.vopros.backend.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.dao.HibernateProyectoDAO;
import tk.vopros.backend.model.Proyecto;

public class ProyectoDAOTestCase {

	public HibernateProyectoDAO dao = new HibernateProyectoDAO();
	public Proyecto proyecto1;
	public Proyecto proyecto2;

	@Before
	public void setUp() throws Exception {
		proyecto1 = new Proyecto("Proyecto 1 db");
		proyecto2 = new Proyecto("Proyecto 2 db");
	}

	@Test
	public void testGuardarYRecuperarUnProyecto() {
		this.dao.save(this.proyecto1);
		Long id = proyecto1.id;
		Proyecto recuperado = this.dao.getById(id);
		assertEquals(this.proyecto1.id, (recuperado.id));
	
		this.dao.delete(proyecto1);
	}

	@Test
	public void testActualizarUnProyecto() {
		this.dao.save(this.proyecto1);
		assertEquals(this.proyecto1.getNombre(), "Proyecto 1 db");
		proyecto1.setNombre("Proyecto update");
		this.dao.update(proyecto1);
		
		Long id = proyecto1.id;
		Proyecto recuperado = this.dao.getById(id);
		assertEquals(recuperado.getNombre(), "Proyecto update");
		
		this.dao.delete(proyecto1);
	}

	@Test
	public void testGetAllProyectos() {
		this.dao.save(this.proyecto1);
		this.dao.save(this.proyecto2);
		ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) this.dao.getAll();
		Integer size = (Integer) proyectos.size();
		Assert.assertTrue(size.equals(2));
		
		this.dao.delete(proyecto1);
		this.dao.delete(proyecto2);
	}

	@Test
	public void testBorrarUnProyecto() {
		this.dao.save(this.proyecto1);
		this.dao.delete(proyecto1);
		ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) this.dao.getAll();
		Integer size = (Integer) proyectos.size();
		Assert.assertTrue(size.equals(0));
	}
}

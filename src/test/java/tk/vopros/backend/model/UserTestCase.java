package tk.vopros.backend.model;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.model.Proyecto;
import tk.vopros.backend.model.User;

public class UserTestCase {
	
	public User user;
	public Proyecto proyecto;
	
	@Before
	public void setUp() throws Exception {
		
		user = new User("unUsuario","unNombre","unApellido","unEmail","unaContrasenha");
		proyecto = new Proyecto("Vopros");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVerificarQueSiCreoUnProyectoSoyElCreador() {

		this.user.nuevoProyecto(proyecto);
		assertEquals(proyecto.getCreador(), user);
	}
}

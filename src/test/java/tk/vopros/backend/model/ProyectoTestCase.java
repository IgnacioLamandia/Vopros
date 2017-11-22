package tk.vopros.backend.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProyectoTestCase {

	public Proyecto proyecto;
	public User user;
	
	@Before
	public void setUp() throws Exception {
		
		proyecto = new Proyecto("Vopros");
		user = new User("unUsuario","unNombre","unApellido","unEmail","unaContrasenha");
		user.nuevoProyecto(proyecto);
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

}

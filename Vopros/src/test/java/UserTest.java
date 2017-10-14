import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.model.Proyecto;
import tk.vopros.backend.model.User;

public class UserTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUnUsuarioCreaUnProyecto() {
		User user = new User("Nacho", "Lamandia","gastonveliez@gmail.com");
		Proyecto proyect = user.nuevoProyecto("Vopros proyect");
		assertEquals(proyect.getCreador(), user);
	}
}

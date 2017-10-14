import static org.junit.Assert.*;

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
	public void test() {
		User user = new User();
		Proyecto proyect = user.nuevoProyecto("Vopros");
		assertEquals(proyect.getCreador(), user);
	}
}

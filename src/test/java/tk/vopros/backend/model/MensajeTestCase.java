package tk.vopros.backend.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MensajeTestCase {
	
	public User emisor;
	public User receptor;
	public Mensaje msg;

	@Before
	public void setUp() throws Exception {
		
		emisor = new User();
		receptor = new User();
		msg = new Mensaje(emisor, receptor, "texto");
	}

	@Test
	public void testGetTexto() {
		assertEquals(msg.getTexto(), "texto");
	}
	
	@Test
	public void testGetYSetEmisor() {
		assertEquals(msg.getEmisor(), emisor);
		
		User user2 = new User();
		msg.setEmisor(user2);
		assertEquals(msg.getEmisor(), user2);
	}
	
	@Test
	public void testGetYSetReceptor() {
		assertEquals(msg.getReceptor(), receptor);
		
		User user2 = new User();
		msg.setReceptor(user2);
		assertEquals(msg.getReceptor(), user2);
	}

}

package tk.vopros.backend.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.User;

public class UserDAOTestCase {
	public HibernateUserDAO dao = new HibernateUserDAO();
	public User user1;
	public User user2;
	public User user3;

	@Before
	public void setUp() throws Exception {
		user1 = new User("User1","UserU", "Uno", "user1@test.db","11111");
		user2 = new User("User2","UserD", "Dos", "user2@test.db","22222");
		user3 = new User("User3","UserT", "Tres", "user3@test.db","33333");
	}

	@Test
	public void testGuardarYRecuperarUnUser() {
		this.dao.save(this.user1);
		Long id = user1.id;
		User recuperado = this.dao.getById(id);
		assertEquals(this.user1.id, (recuperado.id));
	
		this.dao.delete(user1);
	}
	
	public void testGuardarYRecuperarUnUserPorNombreDeUsuario() {
		this.dao.save(this.user1);
		User recuperado = this.dao.getByUsername("User1");
		assertEquals(this.user1.usuario, (recuperado.id));
	
		this.dao.delete(user1);
	}

	@Test
	public void testActualizarUnUser() {
		this.dao.save(this.user1);
		assertEquals(this.user1.usuario, "User1");
		user1.usuario = "UserUno";
		this.dao.update(user1);
		Long id = user1.id;
		User recuperado = this.dao.getById(id);
		assertEquals(recuperado.usuario, "UserUno");
		
		this.dao.delete(user1);
	}

	@Test
	public void testGetAllUsers() {
		this.dao.save(this.user1);
		this.dao.save(this.user2);
		this.dao.save(this.user3);
		ArrayList<User> users = (ArrayList<User>) this.dao.getAll();
		Integer size = (Integer) users.size();
		Assert.assertTrue(size.equals(3));
		
		this.dao.delete(user1);
		this.dao.delete(user2);
		this.dao.delete(user3);

	}

	@Test
	public void testBorrarUnUser() {
		this.dao.save(this.user1);
		this.dao.delete(user1);
		ArrayList<User> users = (ArrayList<User>) this.dao.getAll();
		Integer size = (Integer) users.size();
		Assert.assertTrue(size.equals(0));
	}
}

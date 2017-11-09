package tk.vopros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.User;

@Service
public class UserService {
	
	@Autowired
	private HibernateUserDAO userDAO;
	public UserService(){
		userDAO = new HibernateUserDAO();
	}
	
	@Transactional
	public List<User> getAll(){
		return userDAO.getAll();
	}
	
	@Transactional
	public void setUser(User user) {
		userDAO.save(user);
	}
	@Transactional
	public User getById(Long id) {
		return userDAO.getById(id);
	}
	
	@Transactional
	public User getByUsername(String username) {
		return userDAO.getByUsername(username);
	}
	
	
	@Transactional
	public void updateUser(User user) {
		userDAO.update(user);
	}
	
	@Transactional
	public Boolean validate(User user){
		return userDAO.validate(user);
	}
	
	@Transactional
	public List<User> search(String nombre){
		return userDAO.searchUsers(nombre);
	}
}
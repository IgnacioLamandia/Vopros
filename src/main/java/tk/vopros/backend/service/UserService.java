package tk.vopros.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.User;

@Service
public class UserService {
	private HibernateUserDAO userDAO;
	public UserService(){
		userDAO = new HibernateUserDAO();
	}
	
	public List<User> getAll(){
		return userDAO.getAll();
	}
	
	public void setUser(User user) {
		userDAO.save(user);
	}
	
	public Boolean validate(User user){
		return userDAO.validate(user);
	}
}
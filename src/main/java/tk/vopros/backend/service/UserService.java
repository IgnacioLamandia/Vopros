package tk.vopros.backend.service;

import java.util.List;

import tk.vopros.backend.dao.HibernateUserDAO;
import tk.vopros.backend.model.User;


class UserService {
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
	
//	public void validate(User user){
//		userDAO.validate(user);
//	}
}
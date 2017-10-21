package tk.vopros.backend.service

import tk.vopros.backend.dao.HibernateUserDAO
import tk.vopros.backend.model.User


class UserService {
	HibernateUserDAO userDAO;
	new (){
		userDAO = new HibernateUserDAO()
	}
	
	def getAll(){
		return userDAO.getAll
	}
	
	def setUser(User user) {
		userDAO.save(user)
	}
	
	def validate(User user){
		userDAO.validate(user)
	}
}
package tk.vopros.backend.service

import tk.vopros.backend.dao.HibernateUserDAO

class UserService {
	HibernateUserDAO userDAO;
	new (){
		userDAO = new HibernateUserDAO()
	}
	
	def getAll(){
		return userDAO.allUser
	}
}
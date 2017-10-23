package tk.vopros.backend.dao;

import tk.vopros.backend.model.User;

public class HibernateUserDAO extends GenericDAO<User>{
	
	public HibernateUserDAO() {
		super(User.class);
	}

}

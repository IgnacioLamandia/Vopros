package tk.vopros.backend.dao

import tk.vopros.backend.model.User

class HibernateUserDAO extends GenericDAO<User> {

	new() {
		super(User)
	}
}

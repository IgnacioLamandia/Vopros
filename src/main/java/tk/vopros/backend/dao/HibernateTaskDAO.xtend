package tk.vopros.backend.dao

import tk.vopros.backend.model.Task

class HibernateTaskDAO extends GenericDAO<Task> {
	
	new() {
		super(Task)
	}
	
	
}
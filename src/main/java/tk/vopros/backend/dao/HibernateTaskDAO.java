package tk.vopros.backend.dao;

import org.springframework.stereotype.Repository;

import tk.vopros.backend.model.Task;

@Repository
public class HibernateTaskDAO extends GenericDAO<Task>{
	
	public HibernateTaskDAO() {
		super(Task.class);
	}

}

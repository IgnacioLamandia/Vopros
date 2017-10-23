package tk.vopros.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.vopros.backend.dao.HibernateTaskDAO;
import tk.vopros.backend.model.Task;

@Service("taskService")
public class TaskService {
	
	@Autowired
	private HibernateTaskDAO taskDAO;

	public TaskService() {
		taskDAO = new HibernateTaskDAO();
	}

	@Transactional
	public void save(Task task) {
		taskDAO.save(task);
	}

	@Transactional
	public void update(Task task) {
		taskDAO.update(task);
	}

	@Transactional
	public void delete(Long id) {
		taskDAO.delete(getById(id));
	}

	@Transactional
	public Task getById(Long id) {
		return taskDAO.getById(id);
	}

	@Transactional
	public List<Task> getAll() {
		return taskDAO.getAll();
	}

}
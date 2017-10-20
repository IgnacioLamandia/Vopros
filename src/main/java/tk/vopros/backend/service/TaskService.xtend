package tk.vopros.backend.service

import tk.vopros.backend.dao.HibernateTaskDAO
import tk.vopros.backend.model.Task

class TaskService {
var HibernateTaskDAO taskDAO

	new() {
		taskDAO = new HibernateTaskDAO
	}

	def save(Task task) {
		taskDAO.save(task)
	}

	def update(Task task) {
		taskDAO.update(task)
	}

	def delete(Long id) {
		taskDAO.delete(getById(id))
	}

	def getById(Long id) {
		taskDAO.getById(id)
	}

	def getAll() {
		taskDAO.getAll
	}

}
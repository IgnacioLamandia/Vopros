package tk.vopros.backend.dao

import tk.vopros.backend.model.Task
import java.util.List

interface TaskDAO {
	
	def void saveTask(Task task);
	
	def List<Task> getAllTask();
	
	def void delete(Long id);
}
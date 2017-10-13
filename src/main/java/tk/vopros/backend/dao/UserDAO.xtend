package tk.vopros.backend.dao

import tk.vopros.backend.model.User
import java.util.List

interface UserDAO {
	
	def void saveUser(User issue);
	
	def List<User> getAllUser();
	
}

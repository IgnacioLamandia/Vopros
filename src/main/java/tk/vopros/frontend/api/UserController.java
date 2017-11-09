package tk.vopros.frontend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tk.vopros.backend.model.User;
import tk.vopros.backend.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService = new UserService();

	public UserController() {
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")   
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(this.userService.getAll(),HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")   
	public ResponseEntity<Void> createUser(@RequestBody User user) {

			
			this.userService.setUser(user);
			return new ResponseEntity<Void>(HttpStatus.OK);

	}
	
	@RequestMapping(value = "/user/search", method = RequestMethod.POST, produces = "application/json")   
	public ResponseEntity<List<User>> searchUser(@RequestBody String user) {

			List<User> results = this.userService.search(user);
			System.out.println(results);
			
			return new ResponseEntity<List<User>>(results,HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value = "/user/search/{val}", method = RequestMethod.GET, produces = "application/json")   
	public ResponseEntity<List<User>> searchUserName(@PathVariable("val") String user) {

			List<User> results = this.userService.search(user);
			System.out.println(results);
			
			return new ResponseEntity<List<User>>(results,HttpStatus.OK);

	}
	
	
	
	@RequestMapping(value = "/user/byUsername/{username}", method = RequestMethod.GET, produces = "application/json")   
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String user) {

			User result = this.userService.getByUsername(user);
			System.out.println(result.proyectos);
			if(result == null) {return new ResponseEntity<User>(HttpStatus.NOT_FOUND);};
			return new ResponseEntity<User>(result,HttpStatus.OK);

			
			

	}
	
	

}


package tk.vopros.frontend.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tk.vopros.backend.model.Task;
import tk.vopros.backend.model.User;
import tk.vopros.backend.service.UserService;

@RestController
class LoginController {
	
	@Autowired
	UserService userService = new UserService();

	public LoginController() {
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody User input) {
		User user = input;
		System.out.println(input.usuario);
		System.out.println(input.contrasenha);

		Boolean validationStatus = userService.validate(user);

			if(validationStatus){
				String key = "mi_clave";
				long  now = System.currentTimeMillis();
				String jwtTOKEN = Jwts.builder()
								   .signWith(SignatureAlgorithm.HS256,key)
								   .setSubject("JWT")
								   .setIssuedAt(new Date(now))
								   .setExpiration(new Date(now + 900000))
								   .claim("usuario", user.usuario)
								   .claim("email", user.email)
								   .compact();
//				"{ 'JWT': '"+ jwtTOKEN +"' }",
				return new ResponseEntity<String>(HttpStatus.OK);					   
			} else {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
	
		
	}
}

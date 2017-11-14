package tk.vopros.frontend.api;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

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
import io.jsonwebtoken.impl.crypto.MacProvider;
import tk.vopros.backend.model.Task;
import tk.vopros.backend.model.User;
import tk.vopros.backend.security.auth.jwt.Token;
import tk.vopros.backend.service.UserService;

@RestController
class LoginController {
	
	@Autowired
	UserService userService = new UserService();

	public LoginController() {
	}

	@RequestMapping(value = "/auth/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity login(@RequestBody User input) {
		User user = input;


		Boolean validationStatus = userService.validate(user);

			if(validationStatus){
				Key secret = MacProvider.generateKey();
				SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
				byte[] apiKeySecretBytes = secret.getEncoded();
				Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
				
				long  now = System.currentTimeMillis();
				String jwtTOKEN = Jwts.builder()
								   .signWith(signatureAlgorithm,signingKey)
								   .setSubject("JWT")
								   .setIssuedAt(new Date(now))
								   .setExpiration(new Date(now + 900000))
								   .claim("usuario", user.usuario)
								   .claim("email", user.email)
								   .compact();
//				"{ 'JWT': '"+ jwtTOKEN +"' }",
				
				return new ResponseEntity<Token>(new Token(jwtTOKEN),HttpStatus.OK);					   
			} else {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
	
		
	}
}

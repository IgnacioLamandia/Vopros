package tk.vopros.frontend.api
/*
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import org.uqbar.xtrest.api.annotation.Body
import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.json.JSONUtils
import tk.vopros.backend.model.User
import tk.vopros.backend.service.UserService

@Controller
class LoginController {
	extension JSONUtils = new JSONUtils;
	UserService userService = new UserService();

	new() {
	}

	@Post("/login")
	def login(@Body String body) {
		response.contentType = "application/json"
		var User user = body.fromJson(typeof(User))
		val validationStatus = userService.validate(user)
		try{
			if(validationStatus){
				val key = "mi_clave"
				val long  now = System.currentTimeMillis
				val jwtTOKEN = Jwts.builder
								   .signWith(SignatureAlgorithm.HS256,key)
								   .setSubject("JWT")
								   .setIssuedAt(new Date(now))
								   .setExpiration(new Date(now + 900000))
								   .claim("nombre", user.nombre)
								   .claim("email", user.email)
								   .compact
				ok('{ "JWT": "'+ jwtTOKEN +'" }')					   
			} else {
				notFound('{"error": "usuario o contraseña invalida"}')
			}
			
		} catch(UnrecognizedPropertyException exception){
			badRequest('{ "error": "El body debe ser un User" }')
		}	
		
	}
}*/
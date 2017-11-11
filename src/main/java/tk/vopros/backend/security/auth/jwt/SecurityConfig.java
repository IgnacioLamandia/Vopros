package tk.vopros.backend.security.auth.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/authhh/login").permitAll() //permitimos el acceso a /login a cualquiera
            .antMatchers("/favicon.ico").permitAll() 
            .antMatchers("/node_modules/**").permitAll()
            .antMatchers("/angular").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/partials/**").permitAll()
            .antMatchers("/bower_components/**").permitAll()
            //.anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
            .and()
            // Las peticiones /login pasan previamente por este filtro
            .addFilterBefore(new LoginFilter("/authhh/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)

            // Las demás peticiones pasan por este filtro para validar el token
            .addFilterBefore(new JwtFilter(),
                    UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication()
	        .withUser("ask") //Este esta en memoria, falta implementar para autenticar con los usuarios de la bd.
	        .password("123")
	        .roles("ADMIN");
    }
}

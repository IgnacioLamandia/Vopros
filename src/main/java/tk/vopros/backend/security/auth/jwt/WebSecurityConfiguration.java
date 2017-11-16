package tk.vopros.backend.security.auth.jwt;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tk.vopros.backend.dao.HibernateUserDAO;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
  @Autowired
  private HibernateUserDAO userDAO;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService());
  }

  @Bean
  public UserDetailsService userDetailsService() {
      return new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
              String password = null;

              password = userDAO.getByUsername(username).password;
            
              if(password != null) {
                  return  new org.springframework.security.core.userdetails.User(username, password,emptyList());
              } else {
                  throw new UsernameNotFoundException("No existe el usuario '"
                          + username + "'");
              }

          }
      };
  }
}

package tk.vopros.backend.security.auth.jwt;

import static java.util.Collections.emptyList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tk.vopros.backend.model.User;

import tk.vopros.backend.dao.HibernateUserDAO;
////
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
              System.out.println("############################### PASSWORD #################################");
              System.out.println(password);
            
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

class UserX implements UserDetails{
  private String username;
  private String password;

  public UserX(String username, String password){
  	this.username = username;
  	this.password = password;
  }
  
  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
//

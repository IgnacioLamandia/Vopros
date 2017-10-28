package tk.vopros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tk.vopros.backend.dao.GenericDAO;
import tk.vopros.backend.dao.HibernateIssueDAO;
import tk.vopros.backend.service.*;
import tk.vopros.frontend.api.IssueController;

@Configuration
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration(exclude={
	    DataSourceAutoConfiguration.class,
	    DataSourceTransactionManagerAutoConfiguration.class,
	    HibernateJpaAutoConfiguration.class
	})
public class App {
	
	public App() {
		HibernateDataService dataService = new HibernateDataService();
		dataService.createDatosIniciales();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}
}
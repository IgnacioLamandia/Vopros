package tk.vopros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tk.vopros.backend.service.*;

@Configuration
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration(exclude={
	    DataSourceAutoConfiguration.class,
	    DataSourceTransactionManagerAutoConfiguration.class,
	    HibernateJpaAutoConfiguration.class
	})
public class App {
	
	public App() throws IOException {
		HibernateDataService dataService = new HibernateDataService();
		dataService.createDatosIniciales();
		
		//Se corre el servidor de nodejs para el chat.
		String[] command = { "node" ,"src/main/webapp/index.js" }; 
	    Runtime.getRuntime().exec(command);
	    
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}
}
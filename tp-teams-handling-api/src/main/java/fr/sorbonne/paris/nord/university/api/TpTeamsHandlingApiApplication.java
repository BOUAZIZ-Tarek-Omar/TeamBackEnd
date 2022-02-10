package fr.sorbonne.paris.nord.university.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class TpTeamsHandlingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpTeamsHandlingApiApplication.class, args);
	}

}

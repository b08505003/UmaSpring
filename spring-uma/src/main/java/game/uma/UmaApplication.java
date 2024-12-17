package game.uma;

import game.uma.dao.UmaDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UmaDAO umaDAO) {
		return runner -> {
			System.out.println("URL: http://localhost:8080");
		};
	}
}

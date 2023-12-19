package pt.iade.laisferreira.notes.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pt.iade.laisferreira.notes.webserver.models.repositories.NoteRepository;

@SpringBootApplication
public class WebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServerApplication.class, args);
		NoteRepository.populate();
	}

}

package br.com.fiap.moviebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class MovieboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieboxApplication.class, args);
	}

	@RequestMapping("/home")
	public String home(){
		return "Página Inicial!";
	}

}

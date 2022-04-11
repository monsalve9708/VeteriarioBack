package com.veterinaria.veterinaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = JacksonAutoConfiguration.class)
public class VeterinariaApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/huellassanas");
		SpringApplication.run(VeterinariaApplication.class, args);
	}

}

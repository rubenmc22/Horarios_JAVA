package com.cpu.sistema_horario_java;

import com.cpu.sistema_horario_java.app.materia.Materia;
import com.cpu.sistema_horario_java.app.materia.MateriaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SistemaHorario {

	public static void main(String[] args) {
		SpringApplication.run(SistemaHorario.class, args);
	}

	@Bean
	CommandLineRunner init(MateriaRepository matRepo) {

		return args -> {
			log.info("Preloading: " + matRepo.save(new Materia("Matemática")));
			log.info("Preloading: " + matRepo.save(new Materia("Física")));
			log.info("Preloading: " + matRepo.save(new Materia("Química")));
		};
	}

}

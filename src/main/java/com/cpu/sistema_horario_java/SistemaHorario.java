package com.cpu.sistema_horario_java;

import com.cpu.sistema_horario_java.app.materia.Materia;
import com.cpu.sistema_horario_java.app.materia.MateriaRepository;
import com.cpu.sistema_horario_java.app.profesor.Profesor;
import com.cpu.sistema_horario_java.app.profesor.ProfesorRepository;

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
	CommandLineRunner init(MateriaRepository matRepo, ProfesorRepository proRepo) {

		return args -> {
			log.info("************ DATA LOADING ************");

			log.info("Preloading: " + matRepo.save(new Materia("Matemática")));
			log.info("Preloading: " + matRepo.save(new Materia("Física")));
			log.info("Preloading: " + matRepo.save(new Materia("Química")));

			log.info("Preloading: " + proRepo.save(new Profesor("Carlos", "Guillén", "15325648")));
			log.info("Preloading: " + proRepo.save(new Profesor("Horacio", "Beltrán", "6584231")));
			log.info("Preloading: " + proRepo.save(new Profesor("Maria", "Chacín", "19652314")));

			log.info("************ END OF DATA LOADING ************\n");

		};
	}

}

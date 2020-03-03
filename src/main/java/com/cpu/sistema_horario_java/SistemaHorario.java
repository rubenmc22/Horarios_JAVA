package com.cpu.sistema_horario_java;

import java.util.Optional;

import com.cpu.sistema_horario_java.app.materia.Materia;
import com.cpu.sistema_horario_java.app.materia.MateriaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaHorario {

	public static void main(String[] args) {
		SpringApplication.run(SistemaHorario.class, args);
	}

	@Bean
	CommandLineRunner init(MateriaRepository matRepo) {

		return args -> {
			Optional<Materia> optMateria = matRepo.findById(1L);

			if (!optMateria.isPresent()) {
				Materia materia = new Materia();
				materia.setNombre("Matemática");
				matRepo.save(materia);
			}
		};
	}

}

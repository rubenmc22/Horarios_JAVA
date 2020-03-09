package com.cpu.sistema_horario_java;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.asignatura_carga.AsignaturaCargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.Docente;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import com.cpu.sistema_horario_java.config.CustomProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Slf4j
public class SistemaHorario {

	public static void main(String[] args) {
		SpringApplication.run(SistemaHorario.class, args);
	}

	@Bean
	CommandLineRunner init(AsignaturaRepository aRepo, CargaAcademicaRepository cRepo,
			AsignaturaCargaAcademicaRepository acaRepo, DocenteRepository dRepo, CursoRepository cuRepo) {

		return args -> {
			log.info("************ DATA LOADING ************\n");

			log.info("************ CREANDO DOCENTES ************");
			Docente d1 = Docente.builder().nombre("Luis").apellido("Bastidas").cedula("21130426").build();
			log.info("Guardando: " + dRepo.save(d1));
			Docente d2 = Docente.builder().nombre("Maria").apellido("Rorel").cedula("15326958").build();
			log.info("Guardando: " + dRepo.save(d2));
			Docente d3 = Docente.builder().nombre("Juan").apellido("Peña").cedula("6523145").build();
			log.info("Guardando: " + dRepo.save(d3));
			log.info("************ DOCENTES CREADOS ************\n");

			log.info("************ CREANDO ASIGNATURAS ************");
			Asignatura a1 = Asignatura.builder().nombre("Matemática").build();
			log.info("Guardando: " + aRepo.save(a1));
			Asignatura a2 = Asignatura.builder().nombre("Química").build();
			log.info("Guardando: " + aRepo.save(a2));
			Asignatura a3 = Asignatura.builder().nombre("Historia").build();
			log.info("Guardando: " + aRepo.save(a3));
			log.info("************ ASIGNATURAS CREADAS ************\n");

			log.info("************ CREANDO CARGAS ACADEMICAS ************");
			CargaAcademica ca1 = CargaAcademica.builder().nombre("Curriculo 1° año").build();
			log.info("Guardando: " + cRepo.save(ca1));
			CargaAcademica ca2 = CargaAcademica.builder().nombre("Curriculo 2° año").build();
			log.info("Guardando: " + cRepo.save(ca2));
			CargaAcademica ca3 = CargaAcademica.builder().nombre("Curriculo 3° año").build();
			log.info("Guardando: " + cRepo.save(ca3));
			log.info("************ CARGAS ACADEMICAS CREADAS ************\n");

			log.info("************ CREANDO DIAS ************");
			Set<Dia> dias = Arrays.asList(Dia.LUNES, Dia.MARTES, Dia.MIERCOLES, Dia.JUEVES).stream()
					.collect(Collectors.toSet());

			log.info("************ CREANDO CURSOS ************");
			Curso cu1 = Curso.builder().nombre("1° año").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu1));
			Curso cu2 = Curso.builder().nombre("2° año").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu2));
			Curso cu3A = Curso.builder().nombre("3°-A").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu3A));
			Curso cu3B = Curso.builder().nombre("3°-B").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu3B));
			Curso cu4 = Curso.builder().nombre("4° año").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu4));
			Curso cu5 = Curso.builder().nombre("5° año").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu5));

			dias.add(Dia.VIERNES);
			Curso cu6 = Curso.builder().nombre("6° año").dias(dias).build();
			log.info("Guardando: " + cuRepo.save(cu6));
			log.info("************ CURSOS CREADOS ************\n");

			a1.addDocente(d1);
			a1.addDocente(d2);
			log.info("Guardando: " + aRepo.save(a1));

			a2.addDocente(d2);
			log.info("Guardando: " + aRepo.save(a2));

			a3.addDocente(d3);
			log.info("Guardando: " + aRepo.save(a3));

			ca1.addCurso(cu1);
			ca1.addAsignatura(a1);
			ca1.addAsignatura(a2);
			ca1.addAsignatura(a3);
			log.info("Guardando: " + cRepo.save(ca1));

			ca2.addCurso(cu2);
			ca2.addAsignatura(a2);
			ca2.addAsignatura(a3);
			log.info("Guardando: " + cRepo.save(ca2));

			ca3.addCurso(cu3A);
			ca3.addCurso(cu3B);
			log.info("Guardando: " + cRepo.save(ca3));

		};
	}

}

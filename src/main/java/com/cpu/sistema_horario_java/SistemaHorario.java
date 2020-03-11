package com.cpu.sistema_horario_java;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.cpu.sistema_horario_java.app.asignatura.Asignatura;
import com.cpu.sistema_horario_java.app.asignatura.AsignaturaRepository;
import com.cpu.sistema_horario_java.app.carga.CargaAcademica;
import com.cpu.sistema_horario_java.app.carga.CargaAcademicaRepository;
import com.cpu.sistema_horario_java.app.curso.Curso;
import com.cpu.sistema_horario_java.app.curso.CursoRepository;
import com.cpu.sistema_horario_java.app.docente.Docente;
import com.cpu.sistema_horario_java.app.docente.DocenteRepository;
import com.cpu.sistema_horario_java.app.horario.HorarioRepository;
import com.cpu.sistema_horario_java.app.horario.HorarioServiceImpl;
import com.cpu.sistema_horario_java.app.periodo.Periodo;
import com.cpu.sistema_horario_java.app.periodo.PeriodoRepository;
import com.cpu.sistema_horario_java.app.usuario.Usuario;
import com.cpu.sistema_horario_java.app.usuario.UsuarioRepository;
import com.cpu.sistema_horario_java.app.util.types.Dia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SistemaHorario {

	@Autowired
	HorarioServiceImpl hsi;

	public static void main(String[] args) {
		SpringApplication.run(SistemaHorario.class, args);
	}

	@Bean
	CommandLineRunner init(AsignaturaRepository aRepo, CargaAcademicaRepository cRepo, DocenteRepository dRepo,
			CursoRepository cuRepo, PeriodoRepository pRepo, HorarioRepository hr, UsuarioRepository ur) {

		return args -> {
			log.info("************ DATA LOADING ************\n");

			log.info("************ CREANDO DOCENTES ************");
			Docente d1 = Docente.builder().nombre("Serena").apellido("Burgos").cedula("14258369").build();
			log.info("Guardando: " + dRepo.save(d1));
			Docente d2 = Docente.builder().nombre("María").apellido("Estrada").cedula("1235748").build();
			log.info("Guardando: " + dRepo.save(d2));
			Docente d3 = Docente.builder().nombre("Carlos").apellido("Coste").cedula("13369254").build();
			log.info("Guardando: " + dRepo.save(d3));
			Docente d4 = Docente.builder().nombre("Hugo").apellido("Chirinos").cedula("15254789").build();
			log.info("Guardando: " + dRepo.save(d4));
			Docente d5 = Docente.builder().nombre("Luisa").apellido("Castro").cedula("16321456").build();
			log.info("Guardando: " + dRepo.save(d5));
			Docente d6 = Docente.builder().nombre("Olga").apellido("Febles").cedula("17254365").build();
			log.info("Guardando: " + dRepo.save(d6));
			Docente d7 = Docente.builder().nombre("Pedro").apellido("Román").cedula("18954236").build();
			log.info("Guardando: " + dRepo.save(d7));
			Docente d8 = Docente.builder().nombre("Helena").apellido("Narvaes").cedula("19856321").build();
			log.info("Guardando: " + dRepo.save(d8));
			Docente d9 = Docente.builder().nombre("Juan").apellido("Becerra").cedula("20457842").build();
			log.info("Guardando: " + dRepo.save(d9));
			Docente d10 = Docente.builder().nombre("Jhon").apellido("Finol").cedula("21325654").build();
			log.info("Guardando: " + dRepo.save(d10));
			Docente d11 = Docente.builder().nombre("Carol").apellido("Graterol").cedula("15684326").build();
			log.info("Guardando: " + dRepo.save(d11));
			Docente d12 = Docente.builder().nombre("Luna").apellido("Blanco").cedula("14852365").build();
			log.info("Guardando: " + dRepo.save(d12));

			log.info("************ DOCENTES CREADOS ************\n");

			log.info("************ CREANDO ASIGNATURAS ************");
			Asignatura a1 = Asignatura.builder().nombre("Matemática").build();
			log.info("Guardando: " + aRepo.save(a1));
			Asignatura a2 = Asignatura.builder().nombre("Física").build();
			log.info("Guardando: " + aRepo.save(a2));
			Asignatura a3 = Asignatura.builder().nombre("Química").build();
			log.info("Guardando: " + aRepo.save(a3));
			Asignatura a4 = Asignatura.builder().nombre("Historia de Venezuela").build();
			log.info("Guardando: " + aRepo.save(a4));
			Asignatura a5 = Asignatura.builder().nombre("Historia Universal").build();
			log.info("Guardando: " + aRepo.save(a5));
			Asignatura a6 = Asignatura.builder().nombre("Biología").build();
			log.info("Guardando: " + aRepo.save(a6));
			Asignatura a7 = Asignatura.builder().nombre("Psicología").build();
			log.info("Guardando: " + aRepo.save(a7));
			Asignatura a8 = Asignatura.builder().nombre("Inglés").build();
			log.info("Guardando: " + aRepo.save(a8));
			Asignatura a9 = Asignatura.builder().nombre("Castellano").build();
			log.info("Guardando: " + aRepo.save(a9));
			Asignatura a10 = Asignatura.builder().nombre("Programación").build();
			log.info("Guardando: " + aRepo.save(a10));
			Asignatura a11 = Asignatura.builder().nombre("Lógica").build();
			log.info("Guardando: " + aRepo.save(a11));
			Asignatura a12 = Asignatura.builder().nombre("Diseño").build();
			log.info("Guardando: " + aRepo.save(a12));
			log.info("************ ASIGNATURAS CREADAS ************\n");

			log.info("************ CARGAS ACADEMICAS CREADAS ************\n");

			log.info("************ CREANDO USUARIOS ************");
			Usuario u1 = Usuario.builder().cedula("21130426").password("21130426").build();
			log.info("Guardando: " + ur.save(u1));
			Usuario u2 = Usuario.builder().cedula("24367965").password("24367965").build();
			log.info("Guardando: " + ur.save(u2));
			Usuario u3 = Usuario.builder().cedula("20793760").password("20793760").build();
			log.info("Guardando: " + ur.save(u3));
			log.info("************ USUARIOS CREADOS ************\n");

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

			log.info("************ CREANDO BLOQUES HORARIOS ************");
			final LocalTime _7_AM = LocalTime.now().withHour(7).withMinute(0).withSecond(0).withNano(0);
			final LocalTime _7_15_AM = _7_AM.plusMinutes(15);
			final LocalTime _8_AM = _7_15_AM.plusMinutes(45);
			final LocalTime _8_45_AM = _8_AM.plusMinutes(45);
			final LocalTime _9_05_AM = _8_45_AM.plusMinutes(20);
			final LocalTime _9_50_AM = _9_05_AM.plusMinutes(45);
			final LocalTime _10_35_AM = _9_50_AM.plusMinutes(45);
			final LocalTime _11_20_AM = _10_35_AM.plusMinutes(45);
			final LocalTime _12_05_PM = _11_20_AM.plusMinutes(45);
			final LocalTime _12_25_PM = _12_05_PM.plusMinutes(20);
			final LocalTime _1_10_PM = _12_25_PM.plusMinutes(45);
			final LocalTime _1_55_PM = _1_10_PM.plusMinutes(45);
			final LocalTime _2_40_PM = _1_55_PM.plusMinutes(45);
			final LocalTime _3_25_PM = _2_40_PM.plusMinutes(45);
			final LocalTime _4_10_PM = _3_25_PM.plusMinutes(45);

			Periodo p1 = Periodo.builder().bloqueHorario(1).inicioPeriodo(_7_AM).finPeriodo(_7_15_AM).estatus(false)
					.build();
			Periodo p2 = Periodo.builder().bloqueHorario(2).inicioPeriodo(_7_15_AM).finPeriodo(_8_AM).build();
			Periodo p3 = Periodo.builder().bloqueHorario(3).inicioPeriodo(_8_AM).finPeriodo(_8_45_AM).build();
			Periodo p4 = Periodo.builder().bloqueHorario(4).inicioPeriodo(_8_45_AM).finPeriodo(_9_05_AM).estatus(false)
					.build();
			Periodo p5 = Periodo.builder().bloqueHorario(5).inicioPeriodo(_9_05_AM).finPeriodo(_9_50_AM).build();
			Periodo p6 = Periodo.builder().bloqueHorario(6).inicioPeriodo(_9_50_AM).finPeriodo(_10_35_AM).build();
			Periodo p7 = Periodo.builder().bloqueHorario(7).inicioPeriodo(_10_35_AM).finPeriodo(_11_20_AM).build();
			Periodo p8 = Periodo.builder().bloqueHorario(8).inicioPeriodo(_11_20_AM).finPeriodo(_12_05_PM).build();
			Periodo p9 = Periodo.builder().bloqueHorario(9).inicioPeriodo(_12_05_PM).finPeriodo(_12_25_PM)
					.estatus(false).build();
			Periodo p10 = Periodo.builder().bloqueHorario(10).inicioPeriodo(_12_25_PM).finPeriodo(_1_10_PM).build();
			Periodo p11 = Periodo.builder().bloqueHorario(11).inicioPeriodo(_1_10_PM).finPeriodo(_1_55_PM).build();
			Periodo p12 = Periodo.builder().bloqueHorario(12).inicioPeriodo(_1_55_PM).finPeriodo(_2_40_PM).build();
			Periodo p13 = Periodo.builder().bloqueHorario(13).inicioPeriodo(_2_40_PM).finPeriodo(_3_25_PM).build();
			Periodo p14 = Periodo.builder().bloqueHorario(14).inicioPeriodo(_3_25_PM).finPeriodo(_4_10_PM).build();

			log.info("Guardando: " + pRepo.save(p1));
			log.info("Guardando: " + pRepo.save(p2));
			log.info("Guardando: " + pRepo.save(p3));
			log.info("Guardando: " + pRepo.save(p4));
			log.info("Guardando: " + pRepo.save(p5));
			log.info("Guardando: " + pRepo.save(p6));
			log.info("Guardando: " + pRepo.save(p7));
			log.info("Guardando: " + pRepo.save(p8));
			log.info("Guardando: " + pRepo.save(p9));
			log.info("Guardando: " + pRepo.save(p10));
			log.info("Guardando: " + pRepo.save(p11));
			log.info("Guardando: " + pRepo.save(p12));
			log.info("Guardando: " + pRepo.save(p13));
			log.info("Guardando: " + pRepo.save(p14));
			log.info("************ BLOQUES HORARIOS CREADOS ************\n");

			log.info("************ CREANDO CARGAS ACADEMICAS ************");

			List<Integer> horas = new ArrayList<>();

			for (Integer hora : Arrays.asList(1, 2, 3)) {
				horas.add(hora);
			}

			List<Curso> cursos = cuRepo.findAll();
			Collections.shuffle(cursos);

			List<Asignatura> asignaturas = aRepo.findAll();
			Collections.shuffle(asignaturas);

			List<Docente> docentes = dRepo.findAll();
			Collections.shuffle(docentes);

			List<Asignatura> aList = asignaturas;
			List<Docente> dList = docentes;

			Map<Docente, Asignatura> docenteAsignatura = new HashMap<>();

			while (aList.size() > 0) {
				log.info("Creando par docente-asignatura: "
						+ docenteAsignatura.put(getRandomDocente(dList, true), getRandomAsignatura(aList, true)));

			}

			for (Curso curso : cursos) {
				for (Map.Entry<Docente, Asignatura> da : docenteAsignatura.entrySet()) {

					if (isOdd(getRandomHora(horas, false))) {
						CargaAcademica cargaAcademica = CargaAcademica.builder().asignatura(da.getValue())
								.docente(da.getKey()).curso(curso).horas(getRandomHora(horas, false)).build();

						log.info("Creando carga academica: " + cRepo.save(cargaAcademica));
					}
				}
			}

			log.info("************ GENERANDO HORARIOS ************");
			hsi.generar();
			log.info("************ HORARIOS GENERANDOS ************");

		};
	}

	private Docente getRandomDocente(List<Docente> lista, boolean removeElement) {
		int randomIndex = new Random().nextInt(lista.size());
		Docente elemento = lista.get(randomIndex);
		if (removeElement) {
			lista.remove(randomIndex);
		}
		return elemento;
	}

	private Asignatura getRandomAsignatura(List<Asignatura> lista, boolean removeElement) {
		int randomIndex = new Random().nextInt(lista.size());
		Asignatura elemento = lista.get(randomIndex);
		if (removeElement) {
			lista.remove(randomIndex);
		}
		return elemento;
	}

	private Integer getRandomHora(List<Integer> lista, boolean removeElement) {
		int randomIndex = new Random().nextInt(lista.size());
		Integer elemento = lista.get(randomIndex);
		if (removeElement) {
			lista.remove(randomIndex);
		}
		return elemento;
	}

	private boolean isOdd(Integer number) {
		return number % 2 != 0;
	}

}

package com.cpu.sistema_horario_java;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
import com.cpu.sistema_horario_java.app.horario.Horario;
import com.cpu.sistema_horario_java.app.horario.HorarioRepository;
import com.cpu.sistema_horario_java.app.periodo.Periodo;
import com.cpu.sistema_horario_java.app.periodo.PeriodoRepository;
import com.cpu.sistema_horario_java.app.usuario.Usuario;
import com.cpu.sistema_horario_java.app.usuario.UsuarioRepository;
import com.cpu.sistema_horario_java.app.util.types.Dia;

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
	CommandLineRunner init(AsignaturaRepository aRepo, CargaAcademicaRepository cRepo, DocenteRepository dRepo,
			CursoRepository cuRepo, PeriodoRepository pRepo, HorarioRepository hr, UsuarioRepository ur) {

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
			CargaAcademica ca1 = new CargaAcademica();
			CargaAcademica ca2 = new CargaAcademica();
			CargaAcademica ca3 = new CargaAcademica();
			CargaAcademica ca4 = new CargaAcademica();
			log.info("************ CARGAS ACADEMICAS CREADAS ************\n");

			log.info("************ CREANDO HORARIOS ************");
			Horario h1 = new Horario();
			log.info("************ HORARIOS CREADOS ************\n");

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

			log.info("************ CREANDO PeriodoS ************");
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

			log.info("************ PeriodoOS CREADOS ************\n");

			log.info("************ ASIGNACION DE DATOS A CARGA ACADEMICA ************");

			ca1.setAsignatura(a1);
			ca1.setCurso(cu1);
			ca1.setDocente(d1);
			ca1.setHoras(1);
			// log.info("Guardando: " + cRepo.save(ca1));

			ca2.setAsignatura(a2);
			ca2.setCurso(cu2);
			ca2.setDocente(d2);
			ca2.setHoras(2);
			log.info("Guardando: " + cRepo.save(ca2));

			ca3.setAsignatura(a3);
			ca3.setCurso(cu3A);
			ca3.setDocente(d3);
			ca3.setHoras(3);
			log.info("Guardando: " + cRepo.save(ca3));

			ca4.setAsignatura(a1);
			ca4.setCurso(cu3B);
			ca4.setDocente(d2);
			ca4.setHoras(4);
			log.info("Guardando: " + cRepo.save(ca4));

			log.info("************ ASIGNACION DE DATOS A HORARIO ************");

			Map<Dia, Periodo> diaPeriodoUno = new HashMap<>();
			diaPeriodoUno.put(Dia.LUNES, p2);
			diaPeriodoUno.put(Dia.JUEVES, p7);
			h1.setCargaAcademica(ca1);
			h1.setPeriodoDia(diaPeriodoUno);
			log.info("Guardando: " + hr.save(h1));

			log.info("************ FIN DE CARGA DE DATOS ************\n");

		};
	}

}

package com.cpu.sistema_horario_java;

import java.time.LocalTime;

import com.cpu.sistema_horario_java.app.horarios.HorarioServiceImpl;
import com.cpu.sistema_horario_java.app.horas.BloqueHorario;
import com.cpu.sistema_horario_java.app.horas.BloqueHorarioRepository;
import com.cpu.sistema_horario_java.app.usuario.Usuario;
import com.cpu.sistema_horario_java.app.usuario.UsuarioRepository;

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
	CommandLineRunner init(BloqueHorarioRepository pRepo, UsuarioRepository ur) {

		return args -> {
			log.info("************ DATA LOADING ************\n");

			log.info("************ CREANDO USUARIOS ************");
			Usuario u1 = Usuario.builder().cedula("admin").password("admin").build();
			log.info("Guardando: " + ur.save(u1));
			log.info("************ USUARIOS CREADOS ************\n");

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

			BloqueHorario p1 = BloqueHorario.builder().bloqueHorario(1).inicioBloque(_7_AM).finBloque(_7_15_AM)
					.estatus(false).build();
			BloqueHorario p2 = BloqueHorario.builder().bloqueHorario(2).inicioBloque(_7_15_AM).finBloque(_8_AM).build();
			BloqueHorario p3 = BloqueHorario.builder().bloqueHorario(3).inicioBloque(_8_AM).finBloque(_8_45_AM).build();
			BloqueHorario p4 = BloqueHorario.builder().bloqueHorario(4).inicioBloque(_8_45_AM).finBloque(_9_05_AM)
					.estatus(false).build();
			BloqueHorario p5 = BloqueHorario.builder().bloqueHorario(5).inicioBloque(_9_05_AM).finBloque(_9_50_AM)
					.build();
			BloqueHorario p6 = BloqueHorario.builder().bloqueHorario(6).inicioBloque(_9_50_AM).finBloque(_10_35_AM)
					.build();
			BloqueHorario p7 = BloqueHorario.builder().bloqueHorario(7).inicioBloque(_10_35_AM).finBloque(_11_20_AM)
					.build();
			BloqueHorario p8 = BloqueHorario.builder().bloqueHorario(8).inicioBloque(_11_20_AM).finBloque(_12_05_PM)
					.build();
			BloqueHorario p9 = BloqueHorario.builder().bloqueHorario(9).inicioBloque(_12_05_PM).finBloque(_12_25_PM)
					.estatus(false).build();
			BloqueHorario p10 = BloqueHorario.builder().bloqueHorario(10).inicioBloque(_12_25_PM).finBloque(_1_10_PM)
					.build();
			BloqueHorario p11 = BloqueHorario.builder().bloqueHorario(11).inicioBloque(_1_10_PM).finBloque(_1_55_PM)
					.build();
			BloqueHorario p12 = BloqueHorario.builder().bloqueHorario(12).inicioBloque(_1_55_PM).finBloque(_2_40_PM)
					.build();
			BloqueHorario p13 = BloqueHorario.builder().bloqueHorario(13).inicioBloque(_2_40_PM).finBloque(_3_25_PM)
					.build();
			BloqueHorario p14 = BloqueHorario.builder().bloqueHorario(14).inicioBloque(_3_25_PM).finBloque(_4_10_PM)
					.build();

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
			log.info("************ END DATA LOADING ************\n");

		};
	}

}

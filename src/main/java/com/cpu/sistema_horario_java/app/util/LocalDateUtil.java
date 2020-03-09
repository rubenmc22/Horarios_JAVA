package com.cpu.sistema_horario_java.app.util;

import java.time.*;
import java.time.temporal.IsoFields;
import java.util.*;

/**
 * Fecha de Creación: 15/01/2020 Descripción: Clase Singleton que provee una
 * serie de funciones relacionadas con el manejo de Fechas (Java 1.8 - EN
 * CONSTRUCCIÓN).
 *
 * @author mbastidasluis
 */
@SuppressWarnings({ "rawtypes" })
public class LocalDateUtil {

    // GETTER Y SETTER DE PROPIEDADES BASE

    public final LocalTime MIN_TIME = LocalTime.MIN;
    public final LocalTime MAX_TIME = LocalTime.MAX;
    public ZoneId ZONE_REGION = ZoneId.of("America/Caracas");
    public LocalDate NOW = LocalDate.now(ZONE_REGION);
    public int TODAYS_WEEK = NOW.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    public LocalDate FIRS_DAY_THIS_YEAR = NOW.withMonth(1).withDayOfMonth(1);
    public ZoneOffset ZONE_OFFSET = ZONE_REGION.getRules().getOffset(LocalDateTime.now(ZONE_REGION));

    private String langBase = "ES";
    private Map<String, Map> langs = new HashMap<>();

    public LocalDateUtil() {
        setInit();
    }

    /**
     * setea la base del calendario
     */
    private void setInit() {
        Map<String, List> lang = new HashMap<>();
        lang.put("day", Arrays.asList("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"));
        lang.put("month", Arrays.asList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
                "Septiembre", "Octubre", "Noviembre", "Diciembre"));
        lang.put("format", Arrays.asList("dd de MM de YYYY"));
        langs.put("ES", lang);
    }

    public Date asDate(LocalDate localDate, LocalTime localTime) {
        return Date.from(LocalDateTime.of(localDate, localTime).toInstant(ZONE_OFFSET));
    }

    public LocalDate beginWeekDate() {
        return NOW.with(DayOfWeek.MONDAY);
    }

    public LocalDate endWeekDate() {
        return beginWeekDate().plusDays(6);
    }

    public LocalDate beginWeekDate(Integer week) {
        if (week == 1) {
            return FIRS_DAY_THIS_YEAR;
        } else {
            return FIRS_DAY_THIS_YEAR.plusWeeks(week - 1).with(DayOfWeek.MONDAY);
        }
    }

    public Date todayDateMinTime() {
        return asDate(NOW, MIN_TIME);
    }

    public Date todayDateMaxTime() {
        return asDate(NOW, MAX_TIME);
    }

    public Date beginningDatefirstDayLastYear() {
        return asDate(firstDayLastYear(), MIN_TIME);
    }

    public Date endingDateLastDayLastYear() {
        return asDate(lastDayLastYear(), MAX_TIME);
    }

    public LocalDate endWeekDate(Integer weeks) {
        return beginWeekDate(weeks).plusDays(6);
    }

    public LocalDate firstDayOfYear(Integer year) {
        return FIRS_DAY_THIS_YEAR.withYear(year);
    }

    public LocalDate lastDayOfYear(Integer year) {
        return firstDayOfYear(year).plusYears(1).minusDays(1);
    }

    public LocalDate firstDayLastYear() {
        return FIRS_DAY_THIS_YEAR.withYear(FIRS_DAY_THIS_YEAR.getYear() - 1);
    }

    public LocalDate lastDayLastYear() {
        return firstDayLastYear().plusYears(1).minusDays(1);
    }

    /**
     * obtiene el nombre del mes indicado
     *
     * @param month
     * @return
     */
    public String getMonth(Integer month) {
        return getMonth(month, false);
    }

    /**
     * obtiene el nombre del mes indicado
     *
     * @param month
     * @param index
     * @return
     */
    public String getMonth(Integer month, boolean index) {
        String nameDay = "";

        if (month > -1 && month < 13) {
            month -= index ? 1 : 0;

            nameDay = ((List) langs.get(langBase).get("month")).get(month).toString();
        }

        return nameDay;
    }

    /**
     * obtiene el nombre del mes indicado según la semana deseada
     *
     * @param week
     * @return
     */
    public String getMonthByWeek(Integer week) {
        return getMonth((week == 1 ? 0 : beginWeekDate(week).getMonth().getValue() - 1), false);
    }

}

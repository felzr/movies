package com.felzr.movies.api.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

import static java.text.DateFormat.getDateTimeInstance;

/**
 * Classe utilitárias para Datas.
 */
public class DateUtils {

    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String DDMMYYYYHHMM = "dd/MM/yyyy HH:mm";
    public static final String DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String DDMMYYYYKKMMSS = "dd/MM/yyyy kk:mm:ss";
    public static final String HHMM = "HH:mm";
    public static final long MILLIS_MINUTO = 1000l * 60;
    public static final long MILLIS_DIA = MILLIS_MINUTO * 60 * 24;
    public static final int DAYS_OF_MONTH = 30;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DDMMYYYY);

    private DateUtils() {
        // Not implemented
    }

    /**
     * Return the first day of in the biweekly model. If day < 16, return the first day of month
     * If day >= 16, return the 16th day of the month
     *
     * @param date - Reference date
     * @return the first day of the fortnight
     */
    public static Date getBeginningOfBiWeekly(Date date) {

        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (day < 16) {
            return convertToDateViaSqlDate(LocalDate.of(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    1));
        }

        return convertToDateViaSqlDate(LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                16));
    }

    /**
     * Return the last day of in the biweekly model. If day < 16, return the 15th day of month
     * If day >= 16, return the last day of the month
     *
     * @param date - Reference date
     * @return the last day of the fortnight
     */
    public static Date getEndOfBiWeekly(Date date) {

        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (day < 16) {
            return convertToDateViaSqlDate(LocalDate.of(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    15));
        }

        return getEndOfMonth(date);

    }

    public static Date getBeginningOfWeekSunday(Date date) {

        if (date == null) {
            return null;
        }

        /*
            We will force SUNDAY as the first day of the week, hence the Locale.US. If in the future we need this to
            change and respect the country of origin, please create another method and use the Locale.default()
         */

        LocalDate localDate = convertToLocalDateViaSqlDate(date);
        return convertToDateViaSqlDate(localDate.with(WeekFields.of(Locale.US).dayOfWeek(), 1L));
    }

    public static Date getEndOfWeekSunday(Date date) {

        if (date == null) {
            return null;
        }

        LocalDate localDate = convertToLocalDateViaSqlDate(date);
        return convertToDateViaSqlDate(localDate.with(WeekFields.of(Locale.US).dayOfWeek(), 1L).plusDays(6));
    }

    public static Date getBeginningOfMonth(Date date) {

        if (date == null) {
            return null;
        }

        LocalDate localDate = convertToLocalDateViaSqlDate(date);
        return convertToDateViaSqlDate(localDate.with(TemporalAdjusters.firstDayOfMonth()));
    }

    public static Date getBeginningOfBiWeekly() {
        return getBeginningOfBiWeekly(now());
    }

    public static Date getEndOfMonth(Date date) {

        if (date == null) {
            return null;
        }

        LocalDate localDate = convertToLocalDateViaSqlDate(date);
        return convertToDateViaSqlDate(localDate.with(TemporalAdjusters.lastDayOfMonth()));
    }

    /**
     * Retorna um objeto date com o primeiro dia do mes.
     *
     * @param mes 0 a 11
     * @param ano 1800 a 2200
     * @return
     */
    public static Date getDataInicioMes(Integer mes, Integer ano) {
        if (mes < 0 || mes > 11) {
            throw new IllegalArgumentException("Mês informado tem que estar entre 1 e 12.");
        }

        if (ano < 1800 || ano > 2200) {
            throw new IllegalArgumentException("Ano informado tem que estar entre 1800 e 2200.");
        }

        Calendar calInicioMes = new GregorianCalendar();

        calInicioMes.set(Calendar.YEAR, ano);
        calInicioMes.set(Calendar.MONTH, mes);
        calInicioMes.set(Calendar.DAY_OF_MONTH, calInicioMes.getActualMinimum(Calendar.DAY_OF_MONTH));

        calInicioMes.set(Calendar.HOUR_OF_DAY, calInicioMes.getActualMinimum(Calendar.HOUR_OF_DAY));
        calInicioMes.set(Calendar.MINUTE, calInicioMes.getActualMinimum(Calendar.MINUTE));
        calInicioMes.set(Calendar.SECOND, calInicioMes.getActualMinimum(Calendar.SECOND));
        calInicioMes.set(Calendar.MILLISECOND, calInicioMes.getActualMinimum(Calendar.MILLISECOND));

        return calInicioMes.getTime();
    }

    public static Date getDataFimMes(Integer mes, Integer ano) {
        if (mes < 0 || mes > 11) {
            throw new IllegalArgumentException("Mês informado tem que estar entre 0 e 11.");
        }

        if (ano < 1800 || ano > 2200) {
            throw new IllegalArgumentException("Ano informado tem que estar entre 1800 e 2200.");
        }

        Calendar calFimMes = new GregorianCalendar();

        calFimMes.set(Calendar.YEAR, ano);
        calFimMes.set(Calendar.MONTH, mes);
        calFimMes.set(Calendar.DAY_OF_MONTH, calFimMes.getActualMaximum(Calendar.DAY_OF_MONTH));

        calFimMes.set(Calendar.HOUR_OF_DAY, calFimMes.getActualMaximum(Calendar.HOUR_OF_DAY));
        calFimMes.set(Calendar.MINUTE, calFimMes.getActualMaximum(Calendar.MINUTE));
        calFimMes.set(Calendar.SECOND, calFimMes.getActualMaximum(Calendar.SECOND));
        calFimMes.set(Calendar.MILLISECOND, calFimMes.getActualMaximum(Calendar.MILLISECOND));

        return calFimMes.getTime();
    }

    public static List<Date> intervaloToDateList(Date inicio, Date fim) {
        List<Date> resultList = new ArrayList<>();

        Calendar dtInicio = new GregorianCalendar();
        dtInicio.setTime(inicio);

        resultList.add(dtInicio.getTime());
        while (!isMesmoDia(dtInicio.getTime(), fim)) {
            Calendar proximoDia = new GregorianCalendar();
            proximoDia.setTime(dtInicio.getTime());
            proximoDia.add(Calendar.DAY_OF_MONTH, 1);

            dtInicio = proximoDia;
            resultList.add(dtInicio.getTime());
        }
        return resultList;
    }

    /**
     * Remove os objetos data menores que a data enviada.
     *
     * @param dataLimite
     * @return
     */
    public static List<Date> removeDatasMenoresQue(Date dataLimite, List<Date> datas) {
        List<Date> resultList = new ArrayList<>();
        for (Date data : datas) {
            if (data.compareTo(dataLimite) >= 0) {
                resultList.add(data);
            }
        }

        return resultList;
    }

    public static List<Date> removeDatasMenoresQueHoje(List<Date> datas) {
        Calendar ontem = new GregorianCalendar();
        ontem.add(Calendar.DAY_OF_YEAR, -1);

        List<Date> resultList = new ArrayList<>();
        for (Date data : datas) {
            if (data.compareTo(ontem.getTime()) > 0) {
                resultList.add(data);
            }
        }

        return resultList;
    }

    public static boolean areDifferentDays(Date first, Date second) {
        return !isMesmoDia(first, second);
    }

    public static boolean isMesmoDia(Date inicio, Date fim) {
        Calendar dtInicio = new GregorianCalendar();
        dtInicio.setTime(inicio);

        Calendar dtFim = new GregorianCalendar();
        dtFim.setTime(fim);

        return ((dtInicio.get(Calendar.YEAR) == dtFim.get(Calendar.YEAR)) && (dtInicio.get(Calendar.DAY_OF_YEAR) == dtFim
                .get(Calendar.DAY_OF_YEAR)));
    }

    public static boolean isToday(final Date date) {
        return isMesmoDia(new Date(), date);
    }

    public static boolean isMesmoMes(Date inicio, Date fim) {
        Calendar dtInicio = new GregorianCalendar();
        dtInicio.setTime(inicio);

        Calendar dtFim = new GregorianCalendar();
        dtFim.setTime(fim);

        return ((dtInicio.get(Calendar.YEAR) == dtFim.get(Calendar.YEAR)) && (dtInicio.get(Calendar.MONTH) == dtFim
                .get(Calendar.MONTH)));
    }

    public static Date getDataInicioDia(Date dia) {
        if (dia == null) {
            return null;
        }

        long time = dia.getTime();
        Instant instant = Instant.ofEpochMilli(time);

        ZonedDateTime zdtDay = instant.atZone(ZoneId.systemDefault());
        LocalDate day = zdtDay.toLocalDate();
        Calendar calendar = new Calendar.Builder()
                .setDate(day.getYear(), day.getMonthValue() - 1, day.getDayOfMonth())
                .build();

        return calendar.getTime();
    }

    public static Date getDataInicioDia(LocalDate localDate) {

        return getDataInicioDia(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public static Date getDataFimDia(Date dia) {
        if (dia == null) {
            return null;
        }

        Calendar dtInicio = new GregorianCalendar();
        dtInicio.setTime(dia);

        dtInicio.set(Calendar.HOUR_OF_DAY, dtInicio.getActualMaximum(Calendar.HOUR_OF_DAY));
        dtInicio.set(Calendar.MINUTE, dtInicio.getActualMaximum(Calendar.MINUTE));
        dtInicio.set(Calendar.SECOND, dtInicio.getActualMaximum(Calendar.SECOND));
        dtInicio.set(Calendar.MILLISECOND, dtInicio.getActualMaximum(Calendar.MILLISECOND));

        return dtInicio.getTime();
    }

    public static Date getDataFimDia(LocalDate localDate) {
        return getDataFimDia(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public static Date getDataFimSemana() {
        Date dataFimDia = getDataFimDia(new Date());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dataFimDia);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK));

        return calendar.getTime();
    }

    /**
     * Concatena a data enviada com o horario.
     *
     * @param data    - data enviada
     * @param horario - horario formato HH:MM
     * @return
     */
    public static Date concatenaDataHora(Date data, String horario) {
        String[] horaMinuto = horario.split(":");
        Calendar dataHora = new GregorianCalendar();
        dataHora.setTime(data);
        dataHora.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaMinuto[0]));
        dataHora.set(Calendar.MINUTE, Integer.parseInt(horaMinuto[1]));

        return dataHora.getTime();
    }

    public static String formataData(Date data, String formato) {
        return new SimpleDateFormat(formato).format(data);
    }

    public static String getDataDMA(Date data) {
        return new SimpleDateFormat(DDMMYYYY).format(data);
    }

    public static String getDataDMAHM(Date data) {
        return new SimpleDateFormat(DDMMYYYYHHMM).format(data);
    }

    public static Date getDataDMA(String data) throws ParseException {
        return new SimpleDateFormat(DDMMYYYY).parse(data);
    }

    public static String getHoraData(Date data) {
        return new SimpleDateFormat(HHMM).format(data);
    }

    public static String formatDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(DATE_FORMATTER);
    }

    public static Date subtraiHorasData(Date data, int horas) {
        nullDateValidator(data);

        long totalMilis = horas * 60 * 60 * 1000L;

        return new Date(data.getTime() - totalMilis);
    }

    public static Date subtraiMinutosData(Date data, int minutos) {
        nullDateValidator(data);

        long totalMilis = minutos * 60 * 1000L;

        return new Date(data.getTime() - totalMilis);
    }

    public static Date subtraiDiasData(Date data, int dias) {
        nullDateValidator(data);

        long totalMilis = dias * MILLIS_DIA;


        return new Date(data.getTime() - totalMilis);
    }

    public static Date adicionarDiasData(Date data, int dias) {
        nullDateValidator(data);

        long totalMilis = dias * MILLIS_DIA;

        return new Date(data.getTime() + totalMilis);
    }

    public static Date getDayInPastWeek(Date data) {
        return subtraiDiasData(data, 7);
    }

    public static Date adicionaHorasData(Date data, int horas) {
        return subtraiHorasData(data, -horas);
    }

    public static Date adicionaMinutosData(Date data, int minutos) {
        return adicionaTempoData(data, minutos * MILLIS_MINUTO);
    }

    private static Date adicionaTempoData(Date data, long milis) {
        nullDateValidator(data);
        return new Date(data.getTime() + milis);
    }


    public static long getDiasEntre(Date d1, Date d2, boolean inclusive) {
        if (!inclusive)
            return ((d2.getTime() - d1.getTime() + 60 * 60 * 1000L) / (60 * 60 * 1000L * 24));
        else
            return ((d2.getTime() - d1.getTime() + 60 * 60 * 1000L + (60 * 60 * 1000L * 24)) / (60 * 60 * 1000L * 24));
    }

    public static String convertMinutosToHoursFormat(String totalMinutes) {
        try {
            int time = Integer.parseInt(totalMinutes);
            int hours = time / 60;
            int minutes = time % 60;
            return String.format("%d:%02d", hours, minutes);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getMaiorData(Date... datas) {
        Date maior = null;
        for (Date data : datas) {
            if (data == null)
                continue;

            if (maior == null || data.after(maior))
                maior = data;
        }

        return maior;
    }

    public static boolean verificaDataEntreHoras(Date atual, String horaIni, String horaFim) {
        Date dtInicio = DateUtils.concatenaDataHora(atual, horaIni);
        Date dtFim = DateUtils.concatenaDataHora(atual, horaFim);

        if (dtInicio.after(dtFim)) {
            return atual.after(dtFim) && atual.before(dtInicio);
        } else if (dtFim.after(dtInicio)) {
            return atual.after(dtFim) || atual.before(dtInicio);
        }
        return true;
    }

    public static Date transformaDataParaTimeZone(Date dtReferencia, String javaTimeZone) {
        DateFormat dateTimeInstance = getDateTimeInstance();
        dateTimeInstance.setTimeZone(TimeZone.getTimeZone(javaTimeZone));

        SimpleDateFormat dateFormatLocal = new SimpleDateFormat(DateUtils.DDMMYYYYHHMMSS);

        try {
            return dateFormatLocal.parse(dateTimeInstance.format(dtReferencia));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dtReferencia;
    }

    public static Date from(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    public static LocalTime getLocalTimeFrom(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date subtractAbsoluteDaysFromDate(Date date, int daysToSubtract) {
        LocalDate dateToSubstract = getValidLocalDate(date, daysToSubtract);

        dateToSubstract = dateToSubstract.minusDays(daysToSubtract);

        Date dateAfterConversion = convertToDateViaSqlDate(dateToSubstract);

        return new Date(dateAfterConversion.getTime());
    }

    public static Date addAbsoluteDaysToDate(Date date, int daysToAdd) {
        LocalDate dateAdd = getValidLocalDate(date, daysToAdd);

        dateAdd = dateAdd.plusDays(daysToAdd);

        Date dateAfterConversion = convertToDateViaSqlDate(dateAdd);

        return new Date(dateAfterConversion.getTime());
    }

    private static LocalDate getValidLocalDate(Date date, int daysToAdd) {
        nullDateValidator(date);

        if (daysToAdd < 0) {
            throw new IllegalArgumentException("Days used to change date can't be negative.");
        }

        return convertToLocalDateViaSqlDate(date);
    }

    public static int setLimitDaysToOneMonth(int daysToLoad) {
        if (daysToLoad > DAYS_OF_MONTH) daysToLoad = DAYS_OF_MONTH;
        return daysToLoad;
    }

    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {

        if (dateToConvert == null) {
            return null;
        }

        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {

        if (dateToConvert == null) {
            return null;
        }

        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }


    private static void nullDateValidator(Date data) {
        if (data == null) {
            throw new IllegalArgumentException("Date can't be null.");
        }
    }

    public static Date now() {
        return Date.from(Instant.now());
    }

    /**
     * @return <code>true</code> if the dates are the same, or both are <code>null</code>
     */
    public static boolean equals(Date oneDate, Date anotherDate) {
        if (oneDate == null) return anotherDate == null;
        if (anotherDate == null) return false;
        return oneDate.compareTo(anotherDate) == 0;
    }

    public static Date convertToDateDefault(LocalDateTime localDate) {
        Date date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static Date getDatefromYear(String year) {
        String sDate = "01/01/".concat(year);
        Date date = new Date();
        try {
            return date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

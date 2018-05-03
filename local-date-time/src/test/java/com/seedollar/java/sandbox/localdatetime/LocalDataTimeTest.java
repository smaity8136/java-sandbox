package com.seedollar.java.sandbox.localdatetime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class LocalDataTimeTest {


    @Test
    @DisplayName("Test to show LocalDateTime parsing")
    public void testParsing() {
        LocalDate localDate = LocalDate.of(2018, 4, 30);
        Assertions.assertEquals(30, localDate.getDayOfMonth());

        LocalTime localTime = LocalTime.of(19, 25, 54);
        Assertions.assertEquals(19, localTime.getHour());

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        Assertions.assertEquals(Month.APRIL, localDateTime.getMonth());
        Assertions.assertEquals(25, localDateTime.getMinute());
    }

    @Test
    @DisplayName("Test to illustrate LocalDateTime conversioning")
    public void testConversioning() {
        // Convert LocalDate and LocalTime to a LocalDateTime
        LocalDateTime localDateTime1 = LocalDate.parse("2017-07-25").atTime(LocalTime.of(22, 10, 32));
        Assertions.assertEquals(Month.JULY, localDateTime1.getMonth());
        Assertions.assertEquals(32, localDateTime1.getSecond());

        // Convert LocalTime to LocalDateTime
        LocalDateTime localDateTime2 = LocalTime.of(11, 42, 4).atDate(LocalDate.parse("2018-04-16"));
        Assertions.assertEquals(16, localDateTime2.getDayOfMonth());
        Assertions.assertEquals(11, localDateTime2.getHour());

        // Convert LocalDateTime to LocalDate/LocalTime
        LocalDate localDate = LocalDateTime.parse("2017-05-27T07:23").toLocalDate();
        Assertions.assertEquals(Month.MAY, localDate.getMonth());
        LocalTime localTime = LocalDateTime.parse("2017-05-27T07:23").toLocalTime();
        Assertions.assertEquals(23, localTime.getMinute());
    }

    @Test
    @DisplayName("Test to illustate 'plus' and 'minus' methods on LocalDateTime api")
    public void testPlusMinus() {
        LocalDate localDate = LocalDate.of(2017, 11, 9).minusDays(12);
        Assertions.assertEquals(Month.OCTOBER, localDate.getMonth());
        // Minus 2 months
        localDate = localDate.minus(2, ChronoUnit.MONTHS);
        Assertions.assertEquals(Month.AUGUST, localDate.getMonth());

        LocalTime localTime = LocalTime.of(21, 40, 12).plusMinutes(20);
        Assertions.assertEquals(0, localTime.getMinute());
        localTime = localTime.plus(2, ChronoUnit.HOURS);
        Assertions.assertEquals(0, localTime.getHour());
    }

    @Test
    @DisplayName("Test to illustate a temporal adjuster")
    public void testTemporalAdjuster() {
        LocalDate localDate = LocalDate.of(2017, 5, 27).with(TemporalAdjusters.firstDayOfMonth());
        Assertions.assertEquals(1, localDate.getDayOfMonth());
    }

    @Test
    @DisplayName("Test to illustate how to convert java.util.Date to LocalDateTime instance using Instant")
    public void testConvertDateToLocalDateTime() throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = sdf.parse("2018-03-26");

        LocalDateTime convertedLocalDateTime = LocalDateTime.ofInstant(utilDate.toInstant(), ZoneId.systemDefault());
        Assertions.assertEquals(2018, convertedLocalDateTime.getYear());
        Assertions.assertEquals(Month.MARCH, convertedLocalDateTime.getMonth());
        Assertions.assertEquals(26, convertedLocalDateTime.getDayOfMonth());
    }

    @Test
    @DisplayName("Test to illustate how to convert LocalDateTime to java.util.Date")
    public void testConvertLocalDateTimeToUtilDate() {
        LocalDateTime localDateTime = LocalDateTime.parse("2016-12-17T14:42");
        Date convertedDate = Date.from(localDateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(localDateTime)));

        Calendar cal = Calendar.getInstance();
        cal.setTime(convertedDate);
        Assertions.assertEquals(localDateTime.getYear(), cal.get(Calendar.YEAR));
        Assertions.assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
        Assertions.assertEquals(localDateTime.getSecond(), cal.get(Calendar.SECOND));
    }

    @Test
    @DisplayName("Test to illustate the Period class which represents a difference in time between 2 LocalDates")
    public void testPeriod() {
        LocalDate localDateOne = LocalDate.parse("2018-01-27");
        LocalDate localDateTwo = LocalDate.parse("2018-05-04");

        Period between = Period.between(localDateOne, localDateTwo);
        Assertions.assertEquals(3, between.getMonths());
        Assertions.assertEquals(7, between.getDays());
        Assertions.assertEquals("P23Y", Period.ofYears(23).toString());
    }

    @Test
    @DisplayName("Test to illustate the Duration class which represents a difference in time between 2 LocalTime instances")
    public void testDuration() {
        LocalTime localTime1 = LocalTime.parse("09:54:12");
        LocalTime localTime2 = LocalTime.parse("18:11:39");

        Duration between = Duration.between(localTime1, localTime2);
        Assertions.assertEquals(29847, between.getSeconds());

        Assertions.assertEquals(14400, Duration.ofHours(4).getSeconds());
        Assertions.assertEquals("PT2H1M", Duration.ofMinutes(121).toString());
    }

    @Test
    @DisplayName("Test to illustate how to use DateTimeFormatter to convert dates")
    public void testDateTimeFormatterConverter() {
        // Define a date time formatter to convert the following patterns to a LocalDate
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd]");

        LocalDate localDate1 = LocalDate.parse("2016-07-22", dtf);
        LocalDate localDate2 = LocalDate.parse("2014/04/17", dtf);

        Assertions.assertEquals(Month.JULY, localDate1.getMonth());
        Assertions.assertEquals(Month.APRIL, localDate2.getMonth());
    }

}

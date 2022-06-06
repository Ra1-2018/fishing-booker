package com.isa.project.constants;

import com.isa.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class ReservationConstants {
    public static final Long DB_ID = 1L;
    public static final Date DB_START_DATE = new GregorianCalendar(2022, Calendar.JULY, 20).getTime();
    public static final int DB_DURATION = 3;
    public static final int DB_NUMBER_OF_PEOPLE = 5;
    public static final double DB_PRICE = 90;

    public static final Date NEW_START_DATE = new GregorianCalendar(2022, Calendar.JUNE, 10).getTime();
    public static final int NEW_DURATION = 3;
    public static final int NEW_NUMBER_OF_PEOPLE = 2;
    public static final double NEW_PRICE = 150;

    public static final Client DB_CLIENT = new Client(1L, "nikola.iv.99@gmail.com", "ftn", "Nikola", "Ivanovic", "Resavska 5", "Novi Sad", "Serbia", "+381691712999", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    public static final Service DB_COTTAGE = new Cottage(1L, "Koliba Aladin i sinovi", "Prelepa koliba", "Nema pusenja", 30, new HashSet<>(), new HashSet<>(), new CottageOwner(), new HashSet<>(), 5, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new Location()) ;

    public static final Long DB_ID_TO_DELETE = 3L;

    public static final Long CLIENT_ID = 1L;
    public static final int DB_COUNT = 1;
}

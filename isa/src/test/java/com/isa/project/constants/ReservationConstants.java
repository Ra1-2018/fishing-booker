package com.isa.project.constants;

import com.isa.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class ReservationConstants {
    public static final Long DB_ID = 1L;
    public static final Date DB_START_DATE = new GregorianCalendar(2022, 5, 5).getTime();
    public static final int DB_DURATION = 3;
    public static final int DB_NUMBER_OF_PEOPLE = 3;
    public static final double DB_PRICE = 90;

    public static final Date NEW_START_DATE = new GregorianCalendar(2022, 5, 8).getTime();
    public static final int NEW_DURATION = 5;
    public static final int NEW_NUMBER_OF_PEOPLE = 2;
    public static final double NEW_PRICE = 150;

    public static final Client DB_CLIENT = new Client(1L, "doktorpistolj@gmail.com", "ftn", "Doktor", "Pistolj", "Fruskogorska 20", "Novi Sad", "Srbija", "+381691234567", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    public static final Service DB_COTTAGE = new Cottage(1L, "Koliba Aladin i Sinovi", "Prelepa koliba", "Nema pusenja unutra", 30, "Bul. Cara Lazara 13", 3, new HashSet<>(), new CottageOwner(), new HashSet<>(), 7, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());

    public static final Long DB_ID_TO_DELETE = 3L;
}

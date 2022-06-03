package com.isa.project.constants;

import com.isa.project.model.*;

import java.util.*;

public class ActionConstants {
    public static final Long DB_ID = 2L;
    public static final Date DB_START_DATE = new GregorianCalendar(2022, Calendar.JUNE, 6).getTime();
    public static final int DB_DURATION = 4;
    public static final int DB_NUMBER_OF_PEOPLE = 6;
    public static final double DB_PRICE = 100;

    public static final Date NEW_START_DATE = new GregorianCalendar(2022, Calendar.JUNE, 9).getTime();
    public static final int NEW_DURATION = 5;
    public static final int NEW_NUMBER_OF_PEOPLE = 5;
    public static final double NEW_PRICE = 150;

    public static final Service DB_BOAT = new Boat(2L, "Nojeva barka", "Veliki brod", "No pets allowed", 20, "Trawler", "20m", 2, "8-10 MW", "100km/h", "GPS", "3 pecaljke", "Otkazivanje 2 dana unapred", new HashSet<>(), new BoatOwner(), new HashSet<>(), 6, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new Location());

    public static final Long DB_ID_TO_DELETE = 3L;
}

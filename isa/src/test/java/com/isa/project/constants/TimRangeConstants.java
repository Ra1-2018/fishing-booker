package com.isa.project.constants;

import com.isa.project.model.Boat;
import com.isa.project.model.BoatOwner;
import com.isa.project.model.Location;
import com.isa.project.model.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class TimRangeConstants {
    public static final Long DB_ID = 2L;

    public static final Date DB_START_DATE = new GregorianCalendar(2022, Calendar.JUNE, 10).getTime();
    public static final Date DB_END_DATE = new GregorianCalendar(2022, Calendar.JUNE, 30).getTime();

    public static final Date NEW_START_DATE = new GregorianCalendar(2022, Calendar.JUNE, 20).getTime();
    public static final Date NEW_END_DATE = new GregorianCalendar(2022, Calendar.JUNE, 25).getTime();

    public static final Service DB_BOAT = new Boat(2L, "Nojeva barka", "Veliki brod", "No pets allowed", 20, "Trawler", "20m", 2, "8-10 MW", "100km/h", "GPS", "3 pecaljke", "Otkazivanje 2 dana unapred", new HashSet<>(), new BoatOwner(), new HashSet<>(), 6, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new Location());

    public static final Long DB_ID_TO_DELETE = 3L;
}

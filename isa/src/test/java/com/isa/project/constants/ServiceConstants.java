package com.isa.project.constants;

import com.isa.project.model.Cottage;
import com.isa.project.model.CottageOwner;
import com.isa.project.model.Service;
import com.isa.project.model.TimeRange;

import java.util.*;

public class ServiceConstants {

    public static final Long DB_COTTAGE_ID = 1L;
    public static final String DB_COTTAGE_NAME = "Koliba Aladin i sinovi";
    public static final String DB_COTTAGE_DESCRIPTION = "Prelepa koliba";
    public static final String DB_COTTAGE_BEHAVIOR_RULES = "Nema pusenja";
    public static final double DB_COTTAGE_PRICE_PER_DAY = 30;
    public static final int DB_COTTAGE_NUMBER_OF_PEOPLE = 5;
    public static final Set<TimeRange> DB_COTTAGE_FREE_PERIODS = new HashSet<>(Arrays.asList(new TimeRange(1L, new GregorianCalendar(2022, Calendar.JUNE, 9).getTime(), new GregorianCalendar(2022, Calendar.JUNE, 15).getTime(), null)));
}

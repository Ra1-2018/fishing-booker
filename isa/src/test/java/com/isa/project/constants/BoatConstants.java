package com.isa.project.constants;

import com.isa.project.model.*;

import java.util.HashSet;
import java.util.Set;

public class BoatConstants {

    public static final Long DB_ID = 2L;

    public static final String DB_BEHAVIOR_RULES = "No pets allowed";
    public static final String NEW_DB_BEHAVIOR_RULES = "No smoking";

    public static final String DB_NAME = "Nojeva barka";
    public static final String NEW_DB_NAME = "Plutajuci orah";

    public static final String DB_DESCRIPTION = "Veliki brod";
    public static final String NEW_DB_DESCRIPTION = "Mali brod";

    public static final double DB_PRICE_PER_DAY = 20;
    public static final double NEW_DB_PRICE_PER_DAY = 10;

    public static final String DB_TYPE = "Trawler";

    public static final String DB_LENGTH = "20m";
    public static final String NEW_DB_LENGTH = "10m";

    public static final int DB_NUMBER_OF_ENGINES = 2;
    public static final String DB_ENGINE_POWER = "8-10 MW";
    public static final String DB_MAXIMUM_VELOCITY = "100km/h";
    public static final String DB_NAVIGATION_EQUIPMENT = "GPS";
    public static final String DB_FISHING_EQUIPMENT = "3 pecaljke";
    public static final String DB_CANCELLATION_TERMS = "Otkazivanje 2 dana unapred";
    public static final int DB_MAX_NUMBER_OF_PEOPLE = 6;

    public static final Service DB_BOAT = new Boat(DB_ID, DB_NAME, DB_DESCRIPTION, DB_BEHAVIOR_RULES, DB_PRICE_PER_DAY, DB_TYPE, DB_LENGTH, DB_NUMBER_OF_ENGINES, DB_ENGINE_POWER, DB_MAXIMUM_VELOCITY, DB_NAVIGATION_EQUIPMENT, DB_FISHING_EQUIPMENT, DB_CANCELLATION_TERMS, new HashSet<>(), null, new HashSet<>(), DB_MAX_NUMBER_OF_PEOPLE, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), null);
    public static final int DB_COUNT = 1;
    public static final Long CLIENT_ID = 1L;
    public static final Long DB_OWNER_ID = 3L;
}

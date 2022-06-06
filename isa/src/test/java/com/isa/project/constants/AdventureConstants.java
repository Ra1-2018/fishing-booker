package com.isa.project.constants;

import com.isa.project.model.Adventure;
import com.isa.project.model.ReservationCancellation;
import com.isa.project.model.Service;
import com.isa.project.model.ServiceType;

import java.util.HashSet;

public class AdventureConstants {

    public static final Long DB_ID = 3L;

    public static final String DB_BEHAVIOR_RULES = "Nema psovanja";
    public static final String NEW_DB_BEHAVIOR_RULES = "Ne psuj";

    public static final String DB_NAME = "Pecajte s nama";
    public static final String NEW_DB_NAME = "Pecajte sa nama";

    public static final String DB_DESCRIPTION = "Najzanimljivija pecaroska avantura";
    public static final String NEW_DB_DESCRIPTION = "Skoro najzanimljivija pecaroska avantura";

    public static final double DB_PRICE_PER_DAY = 40;
    public static final double NEW_DB_PRICE_PER_DAY = 10;

    public static final ServiceType DB_TYPE = ServiceType.ADVENTURE;
    public static final String DB_FISHING_EQUIPMENT = "Mamci";
    public static final ReservationCancellation DB_CANCELLATION = ReservationCancellation.FREE;
    public static final int DB_MAX_NUMBER_OF_PEOPLE = 6;
    public static final String DB_INSTRUCTOR_BIOGRAPHY = "Mnogo dobar covek";


    public static final Service DB_ADVENTURE = new Adventure(DB_ID, DB_NAME, DB_DESCRIPTION, DB_BEHAVIOR_RULES, DB_PRICE_PER_DAY, DB_INSTRUCTOR_BIOGRAPHY, new HashSet<>(), DB_FISHING_EQUIPMENT, DB_CANCELLATION, null, new HashSet<>(), DB_MAX_NUMBER_OF_PEOPLE, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>(), null);
    public static final int DB_COUNT = 1;
    public static final Long CLIENT_ID = 1L;
    public static final Long DB_OWNER_ID = 4L;
}

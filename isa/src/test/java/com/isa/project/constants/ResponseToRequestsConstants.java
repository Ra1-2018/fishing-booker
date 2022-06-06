package com.isa.project.constants;

import com.isa.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class ResponseToRequestsConstants {

    //DB_RESPONSE_TO_DELETION_REQUEST------------------------------------------
    public static final Long DB_COMPLAINT_ID = 1L;
    //administrator
    //deletion request
    public static final String DB_COMPLAINT_CONTENT = "Ma bas lose";
    public static final Date DB_COMPLAINT_UPDATE_DATE = new Date();
    //-------------------------------------------------------------------------

    //DB_DELETION_REQUEST------------------------------------------------------
    public static final Long DB_REQUEST_ID = 1L;
    public static final String DB_REQUEST_USER_EMAIL = "nikola.iv.99@gmail.com";
    public static final String DB_REQUEST_EXPLANATION = "Molim te obrisi me";
    //response
    public static final Date DB_REQUEST_UPDATE_DATE = new Date();
    //-------------------------------------------------------------------------

    public static final Long CLIENT_ID = 1L;
    public static final Long DB_ADMIN_ID = 5L;
    public static final int DB_COUNT = 1;
}

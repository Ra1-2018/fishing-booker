package com.isa.project.constants;

import com.isa.project.model.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class ResponseToRequestsConstants {

    public static final Long DB_ID = 1L;

    public static final Client DB_CLIENT = new Client(1L, "nikola.iv.99@gmail.com", "ftn", "Nikola", "Ivanovic", "Resavska 5", "Novi Sad", "Serbia", "+381691712999", new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>());
    public static final Administrator DB_ADMINISTRATOR = new Administrator(1L, "lenkaisidora.aleksic@gmail.com", "ftn", "Lenka Isidora", "Aleksic", "Safarikova 30", "Zrenjanin", "Serbia", "+381691712999", new HashSet<>(), new HashSet<>());
    public static final ResponseToDeletionRequest DB_RESPONSE_TO_DELETION_REQUEST = new ResponseToDeletionRequest(1L, DB_ADMINISTRATOR, null, "Ne mogu te obrisati");
    public static final DeletionRequest DB_DELETION_REQUEST = new DeletionRequest(1L, "nikola.iv.99@gmail.com", "Molim te obrisi me", DB_RESPONSE_TO_DELETION_REQUEST, new Date()) ;
    public static final ResponseToDeletionRequest DB_RESPONSE_TO_DELETION_REQUEST2 = new ResponseToDeletionRequest(1L, DB_ADMINISTRATOR, DB_DELETION_REQUEST, "Ne mogu te obrisati");

    //DB_RESPONSE_TO_DELETION_REQUEST------------------------------------------
    public static final Long DB_RESPONSE_ID = 1L;
    //administrator
    //deletion request
    public static final String DB_RESPONSE_CONTENT = "Ne mogu te obrisati";
    //-------------------------------------------------------------------------

    //DB_DELETION_REQUEST------------------------------------------------------
    public static final Long DB_REQUEST_ID = 1L;
    public static final String DB_REQUEST_USER_EMAIL = "nikola.iv.99@gmail.com";
    public static final String DB_REQUEST_EXPLANATION = "Molim te obrisi me";
    //response
    public static final Date DB_REQUEST_UPDATE_DATE = new Date();
    //-------------------------------------------------------------------------

    public static final Long CLIENT_ID = 1L;
    public static final int DB_COUNT = 1;
}

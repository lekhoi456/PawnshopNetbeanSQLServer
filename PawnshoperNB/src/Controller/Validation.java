package Controller;

import View.NewContract;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validation {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Locale localeEN = new Locale("en", "EN");
    private static final NumberFormat en = NumberFormat.getInstance(localeEN);
    private static final String PHONE_VALID = "^\\d{10}\\d*$";
    private static final String EMAIL_VALID = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static java.util.Date currentDate() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            java.util.Date date = dateFormat.parse(dtf.format(now));
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean phoneNumber(String phoneNumber) {
        if (phoneNumber.matches(PHONE_VALID)) {
            return true;
        }
        return false;
    }

    public static boolean email(String email) {
        if (email.matches(EMAIL_VALID)) {
            return true;
        }
        return false;
    }

    public static boolean dateFormat(String date) {
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean longFormat(String longStr) {
        try {
            Long.parseLong(longStr.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String convertLongFormat(long longNumber) {
        String longStrFormatted = en.format(longNumber);
        return longStrFormatted;
    }
}

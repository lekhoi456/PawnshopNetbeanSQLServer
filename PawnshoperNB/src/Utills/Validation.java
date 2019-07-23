package Utills;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Validation input
 *
 * @author KhoiLeQuoc
 */
public class Validation {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Locale localeEN = new Locale("en", "EN");
    private static final NumberFormat en = NumberFormat.getInstance(localeEN);
    private static final String[] phoneArray = {"03", "070", "079", "076", "078", "083", "085", "081", "082", "056", "058", "059", "024", "028", "0203", "0204",
        "0205", "0206", "0207", "0208", "0209", "0212", "0213", "0214", "0215", "0216", "0220", "0221", "0222", "0225", "0226", "0227", "0228", "0229", "0232",
        "0233", "0234", "0235", "0236", "0237", "0238", "0239", "0251", "0252", "0254", "0255", "0256", "0257", "0258", "0259", "0260", "0261", "0262", "0263",
        "0269", "0270", "0271", "0272", "0273", "0274", "0275", "0276", "0277", "0290", "0291", "0292", "0293", "0294", "0296", "0297", "0299"};
    private static final String EMAIL_VALID = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";

    /**
     * subtract 2 given days
     *
     * @param d1
     * @param d2
     * @return d2 - d1
     */
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * get current date
     *
     * @return date
     */
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

    /**
     * check valid phone number
     *
     * @param phoneNumber
     * @return true
     */
    public static boolean phoneNumber(String phoneNumber) {
        boolean isValid = false;
        if (phoneNumber.length() == 10) {
            for (int i = 0; i < phoneArray.length; i++) {
                if (phoneNumber.startsWith(phoneArray[i])) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    /**
     * check valid email address
     *
     * @param email
     * @return true
     */
    public static boolean email(String email) {
        if (email.matches(EMAIL_VALID)) {
            return true;
        }
        return false;
    }

    /**
     * check valid date format
     *
     * @param date
     * @return true
     */
    public static boolean dateFormat(String date) {
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * check valid long format
     *
     * @param longStr
     * @return true
     */
    public static boolean longFormat(String longStr) {
        try {
            Long.parseLong(longStr.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * convert long to long (string) format
     *
     * @param longNumber
     * @return longStrFormatted
     */
    public static String convertLongFormat(long longNumber) {
        String longStrFormatted = en.format(longNumber);
        return longStrFormatted;
    }
}

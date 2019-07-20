package Controller;

import View.NewContract;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validation {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final static Scanner scanner = new Scanner(System.in);

    /**
     * d{10} user must be input 10 number; d* user can input more number or not
     */
    private static final String PHONE_VALID
            = "^\\d{10}\\d*$";

    /**
     * [A-Za-z0-9.-+%]+ user must be input from a-z ignore case,0-9 and .-+%
     * least one times
     *
     * @ user must be input "@" [A-Za-z.-]+ user must be input from a-z ignore
     * case, "." "-" least one times . user must be input "." [A-Za-z]{2,4} user
     * must be input from a-z ignore 2 - 4 times
     */
    private static final String EMAIL_VALID
            = "^[A-Za-z0-9.+-_%]+@[A-Za-z.-]+\\.[A-Za-z]{2,4}$";

    // Check user input number limit
    public static int checkInputIntLimit(int min, int max) {
        // Loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Please try again: ");
            }
        }
    }

    // Check user input string
    public static String checkInputString() {
        // Loop until user input correct
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println("Can't empty!");
                System.out.print("Please try again: ");
            } else {
                return result;
            }
        }
    }

    //Check user input int
    public static int checkInputInt() {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Please input number integer");
                System.out.print("Enter again: ");
            }
        }
    }

    // Check phone is number with minimum 10 characters
    // Check email with format <account name>@<domain>. (eg: daql@fe.edu.vn)
    public static String checkInputEmail() {
        // Loop until user input correct
        while (true) {
            String result = checkInputString();
            // Check user input email valid
            if (result.matches(EMAIL_VALID)) {
                return result;
            } else {
                System.out.println("Email with format <account name>@<domain>");
                System.out.print("Please try again: ");
            }
        }
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

    public static boolean dateFormat(String date) {
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}

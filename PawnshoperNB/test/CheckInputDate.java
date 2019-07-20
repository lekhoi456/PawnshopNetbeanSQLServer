
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckInputDate {

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(isValidDate("2014-03-20"));
        System.out.println(isValidDate("11-04-2015 22:01:33:023"));

        System.out.println(isValidDate("32476347656435"));
    }
}

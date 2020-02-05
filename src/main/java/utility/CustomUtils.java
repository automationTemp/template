package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomUtils {

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat mf = new SimpleDateFormat("MM");
    SimpleDateFormat yf = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfFull = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    Calendar calendar;

    public String getCurrentTime() {
        calendar = Calendar.getInstance();
        return sdfFull.format(calendar.getTime());
    }
}
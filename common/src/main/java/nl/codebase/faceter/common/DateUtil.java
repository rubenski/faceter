package nl.codebase.faceter.common;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by rubenski on 7/6/2016.
 */
public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String HH_MM_SS = "HH:mm:ss";

    public static String format(GregorianCalendar calendar, String format){
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        fmt.setCalendar(calendar);
        return fmt.format(calendar.getTime());
    }
}

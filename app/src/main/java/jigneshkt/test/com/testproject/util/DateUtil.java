package jigneshkt.test.com.testproject.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateForFlightSchedule(){
        Date cDate = new Date();
        return  new SimpleDateFormat("yyyy-MM-dd").format(cDate);
    }

}

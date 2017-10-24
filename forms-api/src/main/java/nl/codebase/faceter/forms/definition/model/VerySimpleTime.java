package nl.codebase.faceter.forms.definition.model;

import java.time.LocalTime;

/**
 * Created by rubenski on 7/9/2016.
 */
public class VerySimpleTime {

    private int hour;
    private int minute;
    private int second;

    public VerySimpleTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public static VerySimpleTime fromLocalTime(LocalTime localTime){
        return new VerySimpleTime(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
    }
}

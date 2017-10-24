package nl.codebase.faceter.forms.definition.model;

/**
 * Created by ruben on 11/08/15.
 */
public class TimeDuration {

    private int sign;
    private int hours;
    private int minutes;
    private int seconds;

    public TimeDuration(int sign, int hours, int minutes, int seconds) {
        this.sign = sign;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getSign() {
        return sign;
    }
}

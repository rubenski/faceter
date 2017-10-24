package nl.codebase.faceter.forms.definition.model;

import java.time.LocalDate;

/**
 * Created by rubenski on 7/8/2016.
 */
public class VerySimpleDate {

    private int day;
    private int month;
    private int year;

    public VerySimpleDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static VerySimpleDate fromLocalDate(LocalDate date){
        return new VerySimpleDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
}

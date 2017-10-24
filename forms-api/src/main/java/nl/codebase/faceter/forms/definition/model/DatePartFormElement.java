package nl.codebase.faceter.forms.definition.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by ruben on 11/09/15.
 */
public class DatePartFormElement {

    private VerySimpleDate defaultSelectedDate;
    private List<SelectOption> years = Collections.singletonList(new SelectOption("" + LocalDate.now().getYear(),
            "" + LocalDate.now().getYear()));
    private List<SelectOption> months = defaultOptions(FormsConstants.MONTHS_IN_YEAR);
    private List<SelectOption> days = defaultOptions(FormsConstants.DAYS_IN_MONTH);
    private VerySimpleDate minDate;
    private VerySimpleDate maxDate;

    private static List<SelectOption> defaultOptions(Map<Integer, String> defaults){
        List<SelectOption> options = new ArrayList<>();
        for (Map.Entry<Integer, String> monthEntry : defaults.entrySet()) {
            options.add(new SelectOption(monthEntry.getKey().toString(), monthEntry.getValue()));
        }
        return options;
    }

    public VerySimpleDate getDefaultSelectedDate() {
        return defaultSelectedDate;
    }

    public void setDefaultSelectedDate(VerySimpleDate defaultSelectedDate) {
        this.defaultSelectedDate = defaultSelectedDate;
    }

    public boolean hasDefaultSelectedDate() {
        return defaultSelectedDate != null;
    }

    public List<SelectOption> getYears() {
        return years;
    }

    public void setYears(List<SelectOption> years) {
        this.years = years;
    }

    public List<SelectOption> getMonths() {
        return months;
    }

    public void setMonths(List<SelectOption> months) {
        this.months = months;
    }

    public VerySimpleDate getMinDate() {
        return minDate;
    }

    public void setMinDate(VerySimpleDate minDate) {
        this.minDate = minDate;
    }

    public VerySimpleDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(VerySimpleDate maxDate) {
        this.maxDate = maxDate;
    }

    public List<SelectOption> getDays() {
        return days;
    }

    public void setDays(List<SelectOption> days) {
        this.days = days;
    }

}

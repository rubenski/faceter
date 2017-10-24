package nl.codebase.faceter.forms.definition.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ruben on 11/09/15.
 */
public class TimePartFormElement {

    private TimeDuration durationTillDefaultSelectedTime;
    private VerySimpleTime defaultSelectedTime;
    private List<SelectOption> hourOptions = defaultOptions(FormsConstants.HOURS_IN_DAY);
    private List<SelectOption> minuteOptions = defaultOptions(FormsConstants.MINUTES_IN_HOUR);
    private List<SelectOption> secondOptions = defaultOptions(FormsConstants.SECONDS_IN_MINUTE);

    private static List<SelectOption> defaultOptions(Map<Integer, String> defaults){
        List<SelectOption> options = new ArrayList<>();
        for (Map.Entry<Integer, String> monthEntry : defaults.entrySet()) {
            options.add(new SelectOption(monthEntry.getKey().toString(), monthEntry.getValue()));
        }
        return options;
    }

    public TimeDuration getDurationTillDefaultSelectedTime() {
        return durationTillDefaultSelectedTime;
    }

    public void setDurationTillDefaultSelectedTime(TimeDuration durationTillDefaultSelectedTime) {
        this.durationTillDefaultSelectedTime = durationTillDefaultSelectedTime;
    }

    public VerySimpleTime getDefaultSelectedTime() {
        return defaultSelectedTime;
    }

    public void setDefaultSelectedTime(VerySimpleTime defaultSelectedTime) {
        this.defaultSelectedTime = defaultSelectedTime;
    }

    public List<SelectOption> getHourOptions() {
        return hourOptions;
    }

    public void setHourOptions(List<SelectOption> hourOptions) {
        this.hourOptions = hourOptions;
    }

    public List<SelectOption> getMinuteOptions() {
        return minuteOptions;
    }

    public void setMinuteOptions(List<SelectOption> minuteOptions) {
        this.minuteOptions = minuteOptions;
    }

    public List<SelectOption> getSecondOptions() {
        return secondOptions;
    }

    public void setSecondOptions(List<SelectOption> secondOptions) {
        this.secondOptions = secondOptions;
    }

    public void emptySecondOptions(){
        this.secondOptions = null;
    }

}

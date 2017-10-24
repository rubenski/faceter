package nl.codebase.faceter.forms.definition.model.transform;

import nl.codebase.faceter.common.Transformer;
import nl.codebase.faceter.forms.definition.model.*;
import nl.codebasesoftware.faceter.generated.*;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DateTimePickerFormElementTransformer extends AbstractFormElementTransformer implements
        Transformer<GeneratedDateTimePickerFormElement, FormElement> {

    public static final DateTimePickerFormElementTransformer INSTANCE = new DateTimePickerFormElementTransformer();

    private DateTimePickerFormElementTransformer() {}

    @Override
    public DateTimePickerFormElement transform(GeneratedDateTimePickerFormElement generatedFormElement) {

        DateTimePickerFormElement dateTimePickerFormElement = new DateTimePickerFormElement(generatedFormElement);

        LocalDateTime now = LocalDateTime.now();

        for (Object dateOrTimePart : generatedFormElement.getDatePartOrTimePartOrValidationGroup()) {
            if (dateOrTimePart instanceof GeneratedDatePart) {

                DatePartFormElement datePartFormElement = new DatePartFormElement();
                GeneratedDatePart datePart = (GeneratedDatePart) dateOrTimePart;

                // Set years
                int endYearYearsFromNow = datePart.getEndYearYearsFromNow();
                int startYearYearsFromNow = datePart.getStartYearYearsFromNow();

                int startYear = now.plusYears(startYearYearsFromNow).getYear();
                int endYear = now.plusYears(endYearYearsFromNow).getYear();

                List<SelectOption> years = new ArrayList<>();
                for (int i = startYear; i <= endYear; i++) {
                    SelectOption option = new SelectOption("" + i, "" + i);
                    years.add(option);
                }

                datePartFormElement.setYears(years);

                Duration tillMaxSelectableDate = datePart.getTillMaxSelectableDate();
                Duration tillMinSelectableDate = datePart.getTillMinSelectableDate();

                LocalDateTime maxTime = addTime(now, tillMaxSelectableDate);
                LocalDateTime minTime = addTime(now, tillMinSelectableDate);

                datePartFormElement.setMaxDate(VerySimpleDate.fromLocalDate(maxTime.toLocalDate()));
                datePartFormElement.setMinDate(VerySimpleDate.fromLocalDate(minTime.toLocalDate()));

                Duration durationTillDefaultSelectedDate = datePart.getDurationTillDefaultSelectedDate();
                XMLGregorianCalendar literalDefaultSelectedDate = datePart.getLiteralDefaultSelectedDate();

                if (durationTillDefaultSelectedDate != null) {
                    LocalDate localDate = addTime(now, durationTillDefaultSelectedDate).toLocalDate();
                    datePartFormElement.setDefaultSelectedDate(VerySimpleDate.fromLocalDate(localDate));
                } else if (literalDefaultSelectedDate != null) {
                    LocalDate localDate = toLocalDate(literalDefaultSelectedDate);
                    datePartFormElement.setDefaultSelectedDate(VerySimpleDate.fromLocalDate(localDate));
                }

                dateTimePickerFormElement.setDate(datePartFormElement);

            } else if (dateOrTimePart instanceof GeneratedTimePart) {

                TimePartFormElement timePartFormElement = new TimePartFormElement();
                GeneratedTimePart timePart = (GeneratedTimePart) dateOrTimePart;
                Duration durationTillDefaultSelectedTime = timePart.getDurationTillDefaultSelectedTime();
                XMLGregorianCalendar literalDefaultSelectedTime = timePart.getLiteralDefaultSelectedTime();

                if(timePart.getHourOptions() != null){
                    List<SelectOption> hourOptions = convertNumericOptionsToSelectOptions(timePart.getHourOptions());
                    timePartFormElement.setHourOptions(hourOptions);
                }

                if(timePart.getMinuteOptions() != null){
                    List<SelectOption> minuteOptions = convertNumericOptionsToSelectOptions(timePart.getMinuteOptions());
                    timePartFormElement.setMinuteOptions(minuteOptions);
                }

                if(timePart.getSecondOptions() != null){
                    List<SelectOption> secondOptions = convertNumericOptionsToSelectOptions(timePart.getSecondOptions());
                    timePartFormElement.setSecondOptions(secondOptions);
                }

                if (durationTillDefaultSelectedTime != null) {
                    LocalTime localTime = addTime(now, durationTillDefaultSelectedTime).toLocalTime();
                    timePartFormElement.setDefaultSelectedTime(determineDefaultSelectedTime(localTime, timePartFormElement));
                } else if (literalDefaultSelectedTime != null) {
                    LocalTime localTime = toLocalTime(literalDefaultSelectedTime);
                    timePartFormElement.setDefaultSelectedTime(determineDefaultSelectedTime(localTime, timePartFormElement));
                }

                Boolean includeSeconds = timePart.isIncludeSeconds();
                if(Boolean.FALSE.equals(includeSeconds)) {
                    timePartFormElement.emptySecondOptions();
                }

                dateTimePickerFormElement.setTime(timePartFormElement);
            }
        }

        return dateTimePickerFormElement;
    }

    private VerySimpleTime determineDefaultSelectedTime(LocalTime selectThis, TimePartFormElement timePart) {

        int selectedHour;
        int selectedMinute;
        int selectedSecond;

        if (numericOptionsContains(timePart.getHourOptions(), selectThis.getHour())) {
            selectedHour = selectThis.getHour();
        } else {
            selectedHour = selectClosestNumber(timePart.getHourOptions(), selectThis.getHour());
        }

        if (numericOptionsContains(timePart.getMinuteOptions(), selectThis.getMinute())) {
            selectedMinute = selectThis.getMinute();
        } else {
            selectedMinute = selectClosestNumber(timePart.getMinuteOptions(), selectThis.getMinute());
        }

        if (numericOptionsContains(timePart.getSecondOptions(), selectThis.getSecond())) {
            selectedSecond = selectThis.getSecond();
        } else {
            selectedSecond = selectClosestNumber(timePart.getSecondOptions(), selectThis.getSecond());
        }

        return new VerySimpleTime(selectedHour, selectedMinute, selectedSecond);
    }

    private int selectClosestNumber(List<SelectOption> options, int numberToSelect) {

        Integer difference = null;

        for (SelectOption hourNumberOption : options) {

            int hourOption = Integer.parseInt(hourNumberOption.getValue());
            if (difference == null || Math.abs(numberToSelect - hourOption) < difference) {
                difference = hourOption - numberToSelect;
            }
        }

        return numberToSelect + difference;
    }

    private boolean numericOptionsContains(List<SelectOption> options, int number) {

        if(options == null) {
            return false;
        }

        for (SelectOption hourNumberOption : options) {
            int hourValue = Integer.parseInt(hourNumberOption.getValue());
            if (hourValue == number) {
                return true;
            }
        }

        return false;
    }

    private LocalDateTime addTime(LocalDateTime ldt, Duration xmlDuration) {

        if (xmlDuration == null) {
            return ldt;
        }

        int sign = xmlDuration.getSign();

        if (sign > 0) {
            ldt = ldt.plus(xmlDuration.getYears(), ChronoUnit.YEARS);
            ldt = ldt.plus(xmlDuration.getMonths(), ChronoUnit.MONTHS);
            ldt = ldt.plus(xmlDuration.getDays(), ChronoUnit.DAYS);
            ldt = ldt.plus(xmlDuration.getHours(), ChronoUnit.HOURS);
            ldt = ldt.plus(xmlDuration.getMinutes(), ChronoUnit.MINUTES);
            ldt = ldt.plus(xmlDuration.getSeconds(), ChronoUnit.SECONDS);
        } else {
            ldt = ldt.minus(xmlDuration.getYears(), ChronoUnit.YEARS);
            ldt = ldt.minus(xmlDuration.getMonths(), ChronoUnit.MONTHS);
            ldt = ldt.minus(xmlDuration.getDays(), ChronoUnit.DAYS);
            ldt = ldt.minus(xmlDuration.getHours(), ChronoUnit.HOURS);
            ldt = ldt.minus(xmlDuration.getMinutes(), ChronoUnit.MINUTES);
            ldt = ldt.minus(xmlDuration.getSeconds(), ChronoUnit.SECONDS);
        }

        return ldt;
    }

    private LocalDate toLocalDate(XMLGregorianCalendar calendar) {
        if (calendar != null) {
            return calendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
        }

        return null;
    }

    private LocalTime toLocalTime(XMLGregorianCalendar calendar) {
        if (calendar != null) {
            return calendar.toGregorianCalendar().toZonedDateTime().toLocalTime();
        }

        return null;
    }

    private List<SelectOption> convertNumericOptionsToSelectOptions(NumericOptions numericOptions) {
        if (numericOptions != null) {
            List<SelectOption> selectOptions = new ArrayList<>();
            for (NumberOption numericOption : numericOptions.getNumberOption()) {
                int value = numericOption.getValue();
                String displayName = numericOption.getDisplayName();
                selectOptions.add(new SelectOption("" + value, displayName));
            }

            return selectOptions;
        }

        return Collections.emptyList();

    }
}

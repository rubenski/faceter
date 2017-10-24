package nl.codebase.faceter.forms.definition.model.transform;

import nl.codebase.faceter.common.DateUtil;
import nl.codebase.faceter.forms.definition.model.Validation;
import nl.codebase.faceter.forms.definition.model.ValidationGroup;
import nl.codebase.faceter.forms.definition.model.ValidationType;
import nl.codebasesoftware.faceter.generated.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

abstract class AbstractFormElementTransformer {

    List<ValidationGroup> convertValidations(List<GeneratedValidationGroup> validationGroup) {

        List<ValidationGroup> groups = new ArrayList<>();

        for (GeneratedValidationGroup generatedValidationGroup : validationGroup) {

            ValidationGroup group = new ValidationGroup();
            String messageKey = generatedValidationGroup.getMessageKey();

            group.setMessageKey(messageKey);

            List<Object> validations = generatedValidationGroup.getValidations();

            for (Object validationObj : validations) {
                if (validationObj instanceof DateAfter) {
                    DateAfter dateAfter = (DateAfter) validationObj;
                    GregorianCalendar gregorianDate = dateAfter.getValue().toGregorianCalendar();
                    String date = DateUtil.format(gregorianDate, DateUtil.YYYY_MM_DD);
                    group.addValidation(new Validation(date, ValidationType.DATE_AFTER));
                } else if (validationObj instanceof DateBefore) {
                    DateBefore dateBefore = (DateBefore) validationObj;
                    GregorianCalendar gregorianDate = dateBefore.getValue().toGregorianCalendar();
                    String date = DateUtil.format(gregorianDate, DateUtil.YYYY_MM_DD);
                    group.addValidation(new Validation(date, ValidationType.DATE_BEFORE));
                } else if(validationObj instanceof TimeBefore) {
                    TimeBefore timeBefore = (TimeBefore) validationObj;
                    GregorianCalendar gregorianTime = timeBefore.getValue().toGregorianCalendar();
                    String time = DateUtil.format(gregorianTime, DateUtil.HH_MM_SS);
                    group.addValidation(new Validation(time, ValidationType.TIME_BEFORE));
                } else if(validationObj instanceof TimeAfter) {
                    TimeAfter timeAfter = (TimeAfter) validationObj;
                    GregorianCalendar gregorianTime = timeAfter.getValue().toGregorianCalendar();
                    String time = DateUtil.format(gregorianTime, DateUtil.HH_MM_SS);
                    group.addValidation(new Validation(time, ValidationType.TIME_AFTER));
                } else if(validationObj instanceof MinLength){
                    MinLength minLength = (MinLength) validationObj;
                    int minLengthInt = minLength.getValue();
                    group.addValidation(new Validation("" + minLengthInt, ValidationType.MIN_LENGTH));
                } else if(validationObj instanceof MaxLength){
                    MaxLength maxLength = (MaxLength) validationObj;
                    int maxLengthInt = maxLength.getValue();
                    group.addValidation(new Validation("" + maxLengthInt, ValidationType.MAX_LENGTH));
                } else if(validationObj instanceof MinIntValue){
                    MinIntValue minIntValue = (MinIntValue) validationObj;
                    int minInt = minIntValue.getValue();
                    group.addValidation(new Validation("" + minInt, ValidationType.MIN_INT_VALUE));
                } else if(validationObj instanceof MaxIntValue){
                    MaxIntValue maxIntValue = (MaxIntValue) validationObj;
                    int maxInt = maxIntValue.getValue();
                    group.addValidation(new Validation("" + maxInt, ValidationType.MAX_INT_VALUE));
                }
            }
            groups.add(group);
        }

        return groups;
    }



}

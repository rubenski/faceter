package nl.codebase.faceter.forms.definition.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 11/01/15.
 */
public class FieldSet {

    private List<FormElement> formElements = new ArrayList<>();
    private String fieldPrefix;
    private Integer maxNumberOfObjects;
    private Integer minNumberOfObjects;

    public FieldSet(List<FormElement> formElements) {
        this.formElements = formElements;
    }

    public FieldSet(List<FormElement> formElements, String fieldPrefix, Integer maxNumberOfObjects, Integer minNumberOfObjects) {
        this.formElements = formElements;
        this.fieldPrefix = fieldPrefix;
        this.maxNumberOfObjects = maxNumberOfObjects;
        this.minNumberOfObjects = minNumberOfObjects;
    }

    public List<FormElement> getFormElements() {
        return formElements;
    }

    public String getFieldPrefix() {
        return fieldPrefix == null ? "" : fieldPrefix + "[0].";
    }

    public Integer getMaxNumberOfObjects() {
        return maxNumberOfObjects;
    }

    public boolean isObject() {
        return fieldPrefix != null;
    }

    public boolean hasMaxNumberOfObjects(){
        return maxNumberOfObjects != null;
    }

    public Integer getMinNumberOfObjects() {
        return minNumberOfObjects;
    }
}

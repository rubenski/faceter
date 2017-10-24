package nl.codebase.faceter.forms.definition.model;

/**
 * Created by ruben on 10/29/15.
 */
public class Validation {

    String value;
    ValidationType type;

    public Validation(String value, ValidationType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public ValidationType getType() {
        return type;
    }

}

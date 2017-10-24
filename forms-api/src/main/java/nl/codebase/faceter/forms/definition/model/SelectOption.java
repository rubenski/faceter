package nl.codebase.faceter.forms.definition.model;

/**
 * Created by ruben on 11/01/15.
 */
public class SelectOption {

    private String value;
    private String text;

    public SelectOption(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}

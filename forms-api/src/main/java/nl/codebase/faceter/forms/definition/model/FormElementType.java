package nl.codebase.faceter.forms.definition.model;

/**
 * Created by ruben on 10/29/15.
 */
public enum FormElementType {

    TEXT_INPUT,
    SELECT,
    TEXTAREA,
    DATETIME_PICKER;

    @Override
    public String toString() {
        return this.name();
    }
}

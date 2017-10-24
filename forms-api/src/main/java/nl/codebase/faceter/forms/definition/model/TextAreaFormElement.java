package nl.codebase.faceter.forms.definition.model;

/**
 * Created by rubenski on 7/8/2016.
 */
public class TextAreaFormElement extends FormElement {

    private String defaultValue;
    private Short cols = 25;
    private Short rows = 5;

    public TextAreaFormElement(AbstractGeneratedFormElement generatedElement) {
        super(generatedElement, FormElementType.TEXTAREA);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Short getCols() {
        return cols;
    }

    public void setCols(Short cols) {
        this.cols = cols;
    }

    public Short getRows() {
        return rows;
    }

    public void setRows(Short rows) {
        this.rows = rows;
    }
}

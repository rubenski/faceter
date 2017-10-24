package nl.codebase.faceter.forms.definition.model;

/**
 * Created by rubenski on 7/8/2016.
 */
public class TextInputFormElement extends FormElement {

    private String defaultValue;

    public TextInputFormElement(AbstractGeneratedFormElement generatedElement) {
        super(generatedElement, FormElementType.TEXT_INPUT);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}



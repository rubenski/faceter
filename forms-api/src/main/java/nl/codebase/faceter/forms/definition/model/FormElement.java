package nl.codebase.faceter.forms.definition.model;

import lombok.Getter;

import java.util.List;

/**
 * Created by ruben on 10/29/15.
 */
@Getter
public class FormElement extends AbstractFormElement {

    protected FormElementType type;
    private String label;
    private boolean required;
    private List<ValidationGroup> validations;
    private String systemName;
    private String className;
    private String value;

    public FormElement(AbstractGeneratedFormElement generatedElement, FormElementType type) {
        this.type = type;
        this.systemName = generatedElement.getName();
        this.className = generatedElement.getClassName();
        this.required = generatedElement.isRequired();
        this.label = generatedElement.getLabel();
    }

    public void setValidations(List<ValidationGroup> validations) {
        this.validations = validations;
    }

    public void setValue(final String value){
        this.value = value;
    }
}

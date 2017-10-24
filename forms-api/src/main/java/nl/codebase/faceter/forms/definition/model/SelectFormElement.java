package nl.codebase.faceter.forms.definition.model;

import java.util.List;


public class SelectFormElement extends FormElement {

    private boolean multiple;
    private List<SelectOption> selectOptions;

    public SelectFormElement(AbstractGeneratedFormElement generatedElement) {
        super(generatedElement, FormElementType.SELECT);
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public List<SelectOption> getSelectOptions() {
        return selectOptions;
    }

    public void setSelectOptions(List<SelectOption> selectOptions) {
        this.selectOptions = selectOptions;
    }
}

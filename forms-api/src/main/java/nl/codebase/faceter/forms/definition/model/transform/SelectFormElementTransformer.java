package nl.codebase.faceter.forms.definition.model.transform;

import nl.codebase.faceter.common.Transformer;
import nl.codebase.faceter.forms.definition.model.FormElement;
import nl.codebase.faceter.forms.definition.model.SelectFormElement;
import nl.codebase.faceter.forms.definition.model.SelectOption;
import nl.codebasesoftware.faceter.generated.GeneratedOption;
import nl.codebasesoftware.faceter.generated.GeneratedSelectFormElement;
import nl.codebasesoftware.faceter.generated.SelectOptions;

import java.util.ArrayList;
import java.util.List;

public class SelectFormElementTransformer extends AbstractFormElementTransformer implements Transformer<GeneratedSelectFormElement, FormElement> {

    public static final SelectFormElementTransformer INSTANCE = new SelectFormElementTransformer();

    private SelectFormElementTransformer(){}

    @Override
    public SelectFormElement transform(GeneratedSelectFormElement generatedFormElement) {

        SelectFormElement formElement = new SelectFormElement(generatedFormElement);
        List<SelectOption> selectOptions = new ArrayList<>();
        formElement.setMultiple(generatedFormElement.isMultiple());
        SelectOptions optionsWrapper = generatedFormElement.getSelectOptions();
        for (Object optionObj : optionsWrapper.getSelectOption()) {
            GeneratedOption generatedOption = (GeneratedOption) optionObj;
            SelectOption selectOption = new SelectOption(generatedOption.getValue(), generatedOption.getText());
            selectOptions.add(selectOption);
        }
        formElement.setSelectOptions(selectOptions);

        return formElement;
    }

}

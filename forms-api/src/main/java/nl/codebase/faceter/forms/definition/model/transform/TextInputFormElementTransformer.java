package nl.codebase.faceter.forms.definition.model.transform;


import nl.codebase.faceter.common.Transformer;
import nl.codebase.faceter.forms.definition.model.FormElement;
import nl.codebase.faceter.forms.definition.model.TextInputFormElement;
import nl.codebasesoftware.faceter.generated.GeneratedTextInputFormElement;

public class TextInputFormElementTransformer extends AbstractFormElementTransformer implements Transformer<GeneratedTextInputFormElement, FormElement> {

    public static final TextInputFormElementTransformer INSTANCE = new TextInputFormElementTransformer();

    private TextInputFormElementTransformer(){}

    @Override
    public TextInputFormElement transform(GeneratedTextInputFormElement generatedElement) {
        TextInputFormElement formElement = new TextInputFormElement(generatedElement);
        formElement.setDefaultValue(generatedElement.getDefaultValue());
        formElement.setValidations(convertValidations(generatedElement.getValidationGroup()));
        return formElement;
    }
}

package nl.codebase.faceter.forms.definition.model.transform;

import nl.codebase.faceter.common.Transformer;
import nl.codebase.faceter.forms.definition.model.FormElement;
import nl.codebase.faceter.forms.definition.model.TextAreaFormElement;
import nl.codebasesoftware.faceter.generated.GeneratedTextAreaFormElement;

public class TextAreaFormElementTransformer extends AbstractFormElementTransformer implements Transformer<GeneratedTextAreaFormElement, FormElement> {

    public static final TextAreaFormElementTransformer INSTANCE = new TextAreaFormElementTransformer();

    private TextAreaFormElementTransformer(){}

    @Override
    public TextAreaFormElement transform(GeneratedTextAreaFormElement generatedFormElement) {
        TextAreaFormElement textAreaFormElement = new TextAreaFormElement(generatedFormElement);
        textAreaFormElement.setValidations(convertValidations(generatedFormElement.getValidationGroup()));
        textAreaFormElement.setDefaultValue(generatedFormElement.getDefaultValue());
        return textAreaFormElement;
    }

}

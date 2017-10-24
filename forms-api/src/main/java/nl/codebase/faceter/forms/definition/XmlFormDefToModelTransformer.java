package nl.codebase.faceter.forms.definition;

import nl.codebase.faceter.common.Transformer;
import nl.codebase.faceter.forms.definition.model.FieldSet;
import nl.codebase.faceter.forms.definition.model.FormDefinition;
import nl.codebase.faceter.forms.definition.model.FormElement;
import nl.codebase.faceter.forms.definition.model.transform.DateTimePickerFormElementTransformer;
import nl.codebase.faceter.forms.definition.model.transform.SelectFormElementTransformer;
import nl.codebase.faceter.forms.definition.model.transform.TextAreaFormElementTransformer;
import nl.codebase.faceter.forms.definition.model.transform.TextInputFormElementTransformer;
import nl.codebasesoftware.faceter.generated.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlFormDefToModelTransformer implements Transformer<GeneratedFormDef, FormDefinition> {

    private static Map<Class, Transformer> TRANSFORMERS = new HashMap<Class, Transformer>(){
        {
            put(GeneratedTextInputFormElement.class, TextInputFormElementTransformer.INSTANCE);
            put(GeneratedSelectFormElement.class, SelectFormElementTransformer.INSTANCE);
            put(GeneratedDateTimePickerFormElement.class, DateTimePickerFormElementTransformer.INSTANCE);
            put(GeneratedTextAreaFormElement.class, TextAreaFormElementTransformer.INSTANCE);
        }
    };

    @Override
    public FormDefinition transform(GeneratedFormDef source) {

        List<FieldSet> fieldSets = new ArrayList<>();

        for (GeneratedFieldSet generatedFieldSet : source.getFieldSet()) {

            List<FormElement> formElements = new ArrayList<>();

            for (Object generatedElement : generatedFieldSet.getElements()) {
                Transformer transformer = TRANSFORMERS.get(generatedElement.getClass());
                FormElement formElement = (FormElement) transformer.transform(generatedElement);
                formElements.add(formElement);
            }

            GeneratedObjectSettings objectSettings = generatedFieldSet.getObjectSettings();

            if(objectSettings != null){
                Integer minObjects = objectSettings.getMinObjects() == null ? null : objectSettings.getMinObjects().intValue();
                Integer maxObjects = objectSettings.getMaxOjects() == null ? null : objectSettings.getMaxOjects().intValue();
                fieldSets.add(new FieldSet(formElements, objectSettings.getNamePrefix(), maxObjects, minObjects));
            } else {
                fieldSets.add(new FieldSet(formElements));
            }
        }

        return new FormDefinition(source.getFormId(), fieldSets, source.getOutputChannel());
    }
}

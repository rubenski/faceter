package nl.codebase.faceter.forms.definition;

import nl.codebase.faceter.forms.definition.model.FormDefinition;
import nl.codebase.faceter.forms.definition.model.FormDefinitionNotFoundException;
import nl.codebase.faceter.forms.definition.model.FormsServiceException;
import nl.codebasesoftware.faceter.generated.GeneratedFormDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormDefinitionServiceImpl implements FormDefinitionService {

    private XmlFormDefinitionReader reader;

    @Autowired
    public FormDefinitionServiceImpl(XmlFormDefinitionReader reader) {
        this.reader = reader;
    }

    @Override
    public List<FormDefinition> findAll() throws FormsServiceException  {
        List<FormDefinition> formDefinitions = new ArrayList<>();
        XmlFormDefToModelTransformer transformer = new XmlFormDefToModelTransformer();
        try {
            List<GeneratedFormDef> formDefs = reader.getAsObjects();
            for (GeneratedFormDef formDef : formDefs) {
                FormDefinition formDefinition = transformer.transform(formDef);
                formDefinitions.add(formDefinition);
            }
        } catch (XmlConfigException e) {
            throw new FormsServiceException(e);
        }

        return formDefinitions;
    }

    public FormDefinition findById(String formId) throws FormsServiceException {
        try {
            Optional<GeneratedFormDef> formDef = reader.getAsObject(formId);
            if(formDef.isPresent()){
                XmlFormDefToModelTransformer transformer = new XmlFormDefToModelTransformer();
                return transformer.transform(formDef.get());
            } else {
                throw new FormDefinitionNotFoundException("Definition named " + formId + " not found");
            }
        } catch (XmlConfigException e) {
            throw new FormsServiceException(e);
        }
    }
}

package nl.codebase.faceter.forms.definition;

import nl.codebase.faceter.forms.definition.model.FormDefinition;
import nl.codebase.faceter.forms.definition.model.FormsServiceException;

import java.util.List;

public interface FormDefinitionService {
    List<FormDefinition> findAll() throws FormsServiceException;
    FormDefinition findById(String formId) throws FormsServiceException;
}

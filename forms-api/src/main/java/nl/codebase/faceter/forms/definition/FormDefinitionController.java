package nl.codebase.faceter.forms.definition;

import nl.codebase.faceter.forms.definition.model.FormDefinition;
import nl.codebase.faceter.forms.definition.model.FormsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormDefinitionController {

    private FormDefinitionServiceImpl formDefinitionService;

    @Autowired
    public FormDefinitionController(final FormDefinitionServiceImpl formDefinitionService) {
        this.formDefinitionService = formDefinitionService;
    }

    @RequestMapping(value = "/definition/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public FormDefinition formDefinition(@PathVariable("id") String formId) throws FormsServiceException {
        return formDefinitionService.findById(formId);
    }
}
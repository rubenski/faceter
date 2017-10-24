package nl.codebase.faceter.forms.definition.model;

import java.util.ArrayList;
import java.util.List;


public class FormDefinition {

    private String formId;
    private String outputChannel;
    private List<FieldSet> fieldSets = new ArrayList<>();

    public FormDefinition(String formId, List<FieldSet> fieldSets, String outputChannel) {
        this.formId = formId;
        this.fieldSets = fieldSets;
        this.outputChannel = outputChannel;
    }

    public String getFormId() {
        return formId;
    }

    public List<FieldSet> getFieldSets() {
        return fieldSets;
    }

    public String getOutputChannel() {
        return outputChannel;
    }
}

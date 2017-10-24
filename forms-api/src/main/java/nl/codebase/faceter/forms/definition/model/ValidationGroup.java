package nl.codebase.faceter.forms.definition.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rubenski on 7/6/2016.
 */
public class ValidationGroup {

    public String messageKey;
    public List<Validation> validations = new ArrayList<>();

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public List<Validation> getValidations() {
        return validations;
    }

    public void setValidations(List<Validation> validations) {
        this.validations = validations;
    }

    public void addValidation(Validation validation){
        this.validations.add(validation);
    }
}

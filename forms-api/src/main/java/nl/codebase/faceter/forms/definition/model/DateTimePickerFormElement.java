package nl.codebase.faceter.forms.definition.model;

/**
 * Created by ruben on 11/09/15.
 */
public class DateTimePickerFormElement extends FormElement {

    private DatePartFormElement date;
    private TimePartFormElement time;

    public DateTimePickerFormElement(AbstractGeneratedFormElement generatedElement) {
        super(generatedElement, FormElementType.DATETIME_PICKER);
    }


    public DatePartFormElement getDate() {
        return date;
    }

    public void setDate(DatePartFormElement date) {
        this.date = date;
    }

    public TimePartFormElement getTime() {
        return time;
    }

    public void setTime(TimePartFormElement time) {
        this.time = time;
    }


}

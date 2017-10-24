import { Component, Input, OnInit }  from '@angular/core';
import { FormGroup }                 from '@angular/forms';
import {QuestionControlService} from "../question/question-control.service";
import {QuestionBase} from "../question/question-base";
import FormSubmitService from "./form-submit-service";


@Component({
    moduleId: module.id,
    selector: 'dynamic-form',
    templateUrl: 'dynamic-form.component.html',
    providers: [ QuestionControlService, FormSubmitService ]
})
export class DynamicFormComponent implements OnInit {

    @Input() questions: QuestionBase<any>[] = [];
    form: FormGroup;
    payLoad = '';

    constructor(private qcs: QuestionControlService, private fss: FormSubmitService) {  }

    ngOnInit() {
        this.form = this.qcs.toFormGroup(this.questions);
    }

    onSubmit() {
        this.payLoad = JSON.stringify(this.form.value);
        this.fss.submitForm(this.form.value);
    }
}

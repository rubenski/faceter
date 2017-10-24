import {Component, OnInit} from "@angular/core";
import {ItemFormService} from './item-form-service'
import {QuestionBase} from "../question/question-base";
import {TextboxQuestion} from "../question/question-textbox";
import {DropdownQuestion} from "../question/question-dropdown";
import {ActivatedRoute, Router, ParamMap} from "@angular/router";
import 'rxjs/add/operator/switchMap';
import {ItemForm} from "./item-form";

@Component({
    // selector: 'my-item-form', This component is linked directly through the router
    template: `
    <div>
      <h2>Item</h2>
      <dynamic-form *ngIf="questions" [questions]="questions"></dynamic-form>
    </div>
  `,
    providers:  [ItemFormService]
})
export class ItemFormComponent implements OnInit {

    questions:  any[];
    form: ItemForm;

    // Continue here. Get parameter from url and fetch

    ngOnInit() {
        console.log(this.route.paramMap);
        var trt;
        this.route.paramMap
            .switchMap((params: ParamMap) =>
                this.service.getItemForm(params.get('type'))).subscribe(f => this.questions = ItemFormComponent.convertToQuestions(f));

        this.route.params.forEach(param => {
            console.log(param);
        });

        // this.service.getItemForm("payment").then( form => this.questions = ItemFormComponent.convertToQuestions(form) )
    }

    constructor(private service: ItemFormService,
                private route: ActivatedRoute,
                private router: Router) {
        //service.getItemForm("payment");
    }

    private static convertToQuestions = function(form) {

        let itemQuestions:QuestionBase<any>[]  = [];

        for(let incomingFieldSet of form.fieldSets) {

            for(let incomingFormElement of incomingFieldSet.formElements) {

                let question;

                switch(incomingFormElement.type) {
                    case "TEXT_INPUT":
                        question = new TextboxQuestion(
                            {
                                value: incomingFormElement.value,
                                key: incomingFormElement.systemName,
                                label: incomingFormElement.label,
                                required: incomingFormElement.required,
                                order: incomingFormElement.order,
                            }
                        );
                        break;
                    case "SELECT":
                        question = new DropdownQuestion(
                            {
                                value: incomingFormElement.value,
                                key: incomingFormElement.systemName,
                                label: incomingFormElement.label,
                                required: incomingFormElement.required,
                                order: incomingFormElement.order,
                                options: incomingFormElement.selectOptions.map(function(element){
                                    element.key = element.value;
                                    element.value = element.text;
                                    return element;
                                })
                            }
                        );
                        break;
                }

                if(question) {
                    itemQuestions.push(question);
                }

            }
        }

        return itemQuestions;
    }
}


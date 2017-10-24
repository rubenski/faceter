"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@angular/core");
const item_form_service_1 = require("./item-form-service");
const question_textbox_1 = require("../question/question-textbox");
const question_dropdown_1 = require("../question/question-dropdown");
const router_1 = require("@angular/router");
require("rxjs/add/operator/switchMap");
let ItemFormComponent = ItemFormComponent_1 = class ItemFormComponent {
    constructor(service, route, router) {
        this.service = service;
        this.route = route;
        this.router = router;
        //service.getItemForm("payment");
    }
    // Continue here. Get parameter from url and fetch
    ngOnInit() {
        console.log(this.route.paramMap);
        var trt;
        this.route.paramMap
            .switchMap((params) => this.service.getItemForm(params.get('type'))).subscribe(f => this.questions = ItemFormComponent_1.convertToQuestions(f));
        this.route.params.forEach(param => {
            console.log(param);
        });
        // this.service.getItemForm("payment").then( form => this.questions = ItemFormComponent.convertToQuestions(form) )
    }
};
ItemFormComponent.convertToQuestions = function (form) {
    let itemQuestions = [];
    for (let incomingFieldSet of form.fieldSets) {
        for (let incomingFormElement of incomingFieldSet.formElements) {
            let question;
            switch (incomingFormElement.type) {
                case "TEXT_INPUT":
                    question = new question_textbox_1.TextboxQuestion({
                        value: incomingFormElement.value,
                        key: incomingFormElement.systemName,
                        label: incomingFormElement.label,
                        required: incomingFormElement.required,
                        order: incomingFormElement.order,
                    });
                    break;
                case "SELECT":
                    question = new question_dropdown_1.DropdownQuestion({
                        value: incomingFormElement.value,
                        key: incomingFormElement.systemName,
                        label: incomingFormElement.label,
                        required: incomingFormElement.required,
                        order: incomingFormElement.order,
                        options: incomingFormElement.selectOptions.map(function (element) {
                            element.key = element.value;
                            element.value = element.text;
                            return element;
                        })
                    });
                    break;
            }
            if (question) {
                itemQuestions.push(question);
            }
        }
    }
    return itemQuestions;
};
ItemFormComponent = ItemFormComponent_1 = __decorate([
    core_1.Component({
        // selector: 'my-item-form', This component is linked directly through the router
        template: `
    <div>
      <h2>Item</h2>
      <dynamic-form *ngIf="questions" [questions]="questions"></dynamic-form>
    </div>
  `,
        providers: [item_form_service_1.ItemFormService]
    }),
    __metadata("design:paramtypes", [item_form_service_1.ItemFormService,
        router_1.ActivatedRoute,
        router_1.Router])
], ItemFormComponent);
exports.ItemFormComponent = ItemFormComponent;
var ItemFormComponent_1;
//# sourceMappingURL=itemform.component.js.map
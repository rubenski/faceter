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
const question_control_service_1 = require("../question/question-control.service");
const form_submit_service_1 = require("./form-submit-service");
let DynamicFormComponent = class DynamicFormComponent {
    constructor(qcs, fss) {
        this.qcs = qcs;
        this.fss = fss;
        this.questions = [];
        this.payLoad = '';
    }
    ngOnInit() {
        this.form = this.qcs.toFormGroup(this.questions);
    }
    onSubmit() {
        this.payLoad = JSON.stringify(this.form.value);
        this.fss.submitForm(this.form.value);
    }
};
__decorate([
    core_1.Input(),
    __metadata("design:type", Array)
], DynamicFormComponent.prototype, "questions", void 0);
DynamicFormComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'dynamic-form',
        templateUrl: 'dynamic-form.component.html',
        providers: [question_control_service_1.QuestionControlService, form_submit_service_1.default]
    }),
    __metadata("design:paramtypes", [question_control_service_1.QuestionControlService, form_submit_service_1.default])
], DynamicFormComponent);
exports.DynamicFormComponent = DynamicFormComponent;
//# sourceMappingURL=dynamic-form.component.js.map
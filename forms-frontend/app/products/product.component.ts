import {QuestionService} from "../question/question-service";
import {Component} from "@angular/core";

@Component({
    // selector: 'my-product', This component is linked directly through the router
    template: `
    <div>
      <h2>Products</h2>
      <dynamic-form [questions]="questions"></dynamic-form>
    </div>
  `,
    providers:  [QuestionService]
})
export class ProductComponent {
    questions: any[];
    constructor(service: QuestionService) {
        this.questions = service.getQuestions();
    }
}

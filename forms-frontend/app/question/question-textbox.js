"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const question_base_1 = require("./question-base");
class TextboxQuestion extends question_base_1.QuestionBase {
    constructor(options = {}) {
        super(options);
        this.controlType = 'textbox';
        this.type = options['type'] || '';
    }
}
exports.TextboxQuestion = TextboxQuestion;
//# sourceMappingURL=question-textbox.js.map
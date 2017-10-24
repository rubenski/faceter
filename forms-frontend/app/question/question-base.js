"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class QuestionBase {
    constructor(options = {}) {
        this.value = options.value;
        this.key = options.key || '';
        this.label = options.label || '';
        this.required = !!options.required;
        this.order = options.order === undefined ? 1 : options.order;
        this.controlType = options.controlType || '';
    }
}
exports.QuestionBase = QuestionBase;
//# sourceMappingURL=question-base.js.map
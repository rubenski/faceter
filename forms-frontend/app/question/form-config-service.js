"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class FormConfigService {
    constructor(appPropertiesService, http) {
        this.appPropertiesService = appPropertiesService;
        this.http = http;
    }
    findFormConfig(id) {
        return this.http.get(this.createUrl(id)).map((res) => res.json());
    }
    createUrl(id) {
        var apiContext = this.appPropertiesService.getProperty("forms-api-context");
        var host = this.appPropertiesService.getProperty("host");
        var port = this.appPropertiesService.getProperty("port");
        var protocol = this.appPropertiesService.getProperty("protocol");
        console.log("api context: " + apiContext);
        var url = protocol + "://" + host + ":" + port + apiContext + "config/" + id;
        console.log("config url: " + url);
        return url;
    }
}
exports.default = FormConfigService;
//# sourceMappingURL=form-config-service.js.map
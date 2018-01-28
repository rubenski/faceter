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
const router_1 = require("@angular/router");
const authentication_service_1 = require("./auth/authentication.service");
const user_service_1 = require("./auth/user.service");
let LoginComponent = class LoginComponent {
    constructor(router, activatedRoute, authenticationService, userService) {
        this.router = router;
        this.activatedRoute = activatedRoute;
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.model = {};
        this.loading = false;
        this.error = '';
        this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
    }
    ngOnInit() {
        this.userService.logout();
    }
    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(result => {
            this.loading = false;
            console.log(result.token);
            if (result) {
                this.userService.login(result.token);
                this.navigateAfterSuccess();
            }
            else {
                this.error = 'Username or password is incorrect';
            }
        }, error => {
            this.error = 'Username or password is incorrect';
            this.loading = false;
        });
    }
    navigateAfterSuccess() {
        if (this.redirectUrl) {
            this.router.navigateByUrl(this.redirectUrl);
        }
        else {
            this.router.navigate(['/']);
        }
    }
};
LoginComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'login',
        templateUrl: 'login.component.html',
        styleUrls: ['login.component.scss']
    }),
    __metadata("design:paramtypes", [router_1.Router,
        router_1.ActivatedRoute,
        authentication_service_1.AuthenticationService,
        user_service_1.UserService])
], LoginComponent);
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map
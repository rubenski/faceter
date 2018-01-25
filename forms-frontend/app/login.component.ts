import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "./auth/authentication.service";
import {UserService} from "./auth/user.service";

@Component({
    moduleId: module.id, // this somehow inserts the 'app' context into the url resolution for the template url. No fucking clue why this works this way.
    selector: 'login',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.scss']
})
export class LoginComponent implements OnInit {

    model: any = {};
    loading = false;
    error = '';
    redirectUrl: string;

    constructor(private router: Router,
                private activatedRoute: ActivatedRoute,
                private authenticationService: AuthenticationService,
                private userService: UserService) {
        this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
    }

    ngOnInit(): void {
        this.userService.logout();
    }

    login() {
        this.loading = true;

        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                result => {
                    this.loading = false;

                    console.log(result.token);

                    if (result) {
                        this.userService.login(result.token);
                        this.navigateAfterSuccess();
                    } else {
                        this.error = 'Username or password is incorrect';
                    }
                },
                error => {
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }
            );
    }

    private navigateAfterSuccess() {
        if (this.redirectUrl) {
            this.router.navigateByUrl(this.redirectUrl);
        } else {
            this.router.navigate(['/']);
        }
    }
}

import {Component, OnInit} from '@angular/core';
import {OktaAuthService} from "@okta/okta-angular";

@Component({
  selector: 'app-login-status',
  templateUrl: './login-status.component.html',
  styleUrls: ['./login-status.component.css']
})
export class LoginStatusComponent implements OnInit {

  isAuthenticated: boolean = false;
  userFullName: string;

  storage: Storage = sessionStorage;

  constructor(private oktaAuthService: OktaAuthService) {
  }

  ngOnInit(): void {
    this.oktaAuthService.$authenticationState.subscribe(result => {
      this.isAuthenticated = result;
      this.getUserDetails();
    });
  }

  private getUserDetails() {
    if (this.isAuthenticated) {
      // fetch the logged in user details (user's claims)

      // user full name is exposed as a property name
      this.oktaAuthService.getUser().then(
        (res) => {
          this.userFullName = res.name!;

          //retrieve user's email from authentication response
          const email = res.email;

          //store user's email in the browser storage
          this.storage.setItem("userEmail", JSON.stringify(email));

        });
    }
  }

  logout() {
    //Terminates the session with Okta and removes current tokens
    this.oktaAuthService.signOut();
  }
}

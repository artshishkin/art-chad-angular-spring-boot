import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {from, Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {OktaAuthService} from "@okta/okta-angular";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor(private oktaAuthService: OktaAuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return from(this.handleAccess(req, next));

  }

  private async handleAccess(req: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {

    // Only add access token to secured endpoint
    const securedEndpoints = [`${environment.backendServerUrl}/api/orders`];

    if (securedEndpoints.some(url => req.urlWithParams.includes(url))) {
      //get access token
      // const accessToken = this.oktaAuthService.getAccessToken();
      const accessToken = await this.oktaAuthService.getAccessToken();
      //todo

      //clone the request and add new header with access token
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${accessToken}`
        }
      });
    }
    return next.handle(req).toPromise();
  }
}

import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {from, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {

  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return from(this.handleAccess(req, next));

  }

  private async handleAccess(req: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {

    throw new Error("NOT IMPLEMENTED YET");
  }
}

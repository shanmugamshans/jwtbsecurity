import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UrlConstants } from '../commonutils/urlConstants';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(public http: HttpClient) { }

  public register(req:any) : Observable<any>{
    return this.http.post(UrlConstants.REGISTER, req);
  }

  public login(req:any) : Observable<any>{
    return this.http.post(UrlConstants.AUTHENTICATE, req);
  }

  public logout() {
    return this.http.get(UrlConstants.LOGOUT)
  }

  getAllUsers(): Observable<any>{
    return this.http.get(UrlConstants.GETALL_USER)
  }

  addNewUser(req:any) : Observable<any> {
    req.role = [{roleName: req.role}];
    return this.http.post(UrlConstants.ADD_NEW_USER, req);
  }
}

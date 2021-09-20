import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private _router: Router, private cookie:CookieService){}
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
    if(state.url==='/login'){
        sessionStorage.clear();
        this.cookie.deleteAll();
        return true;
    }

    if (sessionStorage.getItem('auth-token') && this.cookie.get('usercookies')) {
        return true;
    }

    this._router.navigate(['/login']);
    return false;
}
}

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class HttpinterceptorService implements HttpInterceptor{

  constructor(private spinner: NgxSpinnerService, private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      let clonedRequest = request.clone();

      if (this.router.url != '/login') {

          clonedRequest = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + sessionStorage.getItem('auth-token')) });
            
      }


      // Pass the cloned request instead of the original request to the next handle

      this.spinner.show();

      return next.handle(clonedRequest).pipe(tap((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse && event.status === 200) {
              this.spinner.hide();
          }
      },
          (err: any) => {
              if (err instanceof HttpErrorResponse) {
                  if (err.status === 401 || err.status === 403 || err.status === 500 || err.status === 400) {
                      this.spinner.hide();
                      this.router.navigate(['login']);
                  }

                  return;

              }
          }));


  }
  
}

export const urlInterceptor = [
    {provide: HTTP_INTERCEPTORS, useClass: HttpinterceptorService, multi: true}
]

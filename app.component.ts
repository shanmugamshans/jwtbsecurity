import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private cookie:CookieService, private route:Router){}
  
  title = 'login';

  ngOnInit(): void {
    if(null==this.cookie.get('usercookies')){
      this.route.navigateByUrl('login');
    }

    if(null==sessionStorage.getItem('auth-token')){
      this.route.navigateByUrl('login');
    }
  }
}

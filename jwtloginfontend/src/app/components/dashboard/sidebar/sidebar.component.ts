import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpService } from 'src/app/webservices/http.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  user:any;
  constructor(private router:Router, private cookie:CookieService, private httpService:HttpService) { }

  ngOnInit(): void {
    var userDetails:any = JSON.parse(this.cookie.get('usercookies'));
    this.user = userDetails.user;
  }

  sidebar(){ document.getElementById('page')?.classList.toggle('toggled'); }

  logout(){ 
    this.httpService.logout().subscribe((data:any) => { console.log(data);
      this.router.navigateByUrl('') })
    
    }
}

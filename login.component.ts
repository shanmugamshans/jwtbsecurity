import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { NgxSpinnerService } from 'ngx-spinner';
import { AppConstants } from 'src/app/commonutils/AppConstants';
import { HttpService } from 'src/app/webservices/http.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginPage:boolean = true;
  registerPage:boolean = false;

  registerForm!: FormGroup;
  loginForm!: FormGroup;
  isSubmitted:boolean = false;
  isLoginSubmitted:boolean = false;


  constructor(private fb: FormBuilder, private httpService:HttpService, 
    private cookie:CookieService, private router: Router,private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName:[null,Validators.required],
      lastName:[null, Validators.required],
      email:[null, [Validators.required,Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      password:[null, Validators.required]
    });

    this.loginForm = this.fb.group({
      email:[null, [Validators.required,Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      password:[null, Validators.required]
    })
  }

  get loginForm_valid() { return this.loginForm.controls };
  get registerForm_valid() { return this.registerForm.controls };

  changelogin(val:any){
    if(val=='login'){
      this.loginPage = true;
      this.registerPage = false;
    }else{
      this.loginPage = false;
      this.registerPage = true;
    }
  }

  registerFormSubmit(){

    this.isSubmitted = true;

    if(this.registerForm.invalid){
      return
    }
    this.spinner.show();
    this.isSubmitted = false;

    this.httpService.register(this.registerForm.value).subscribe((data:any) =>{
      this.spinner.hide();
      if(data.status==AppConstants.SUCCESS){
        Swal.fire({
          icon:'success',
          text:AppConstants.SUCCESS
        });
        this.loginPage = !this.loginPage;
        this.registerPage = !this.registerPage;
      }else{
        Swal.fire({
          icon:'error',
          text:data.errMsg
        });
        this.loginPage = !this.loginPage;
        this.registerPage = !this.registerPage;
      }
    })

  }

  loginFormSubmit(){
    
    this.isLoginSubmitted = true;

    if(this.loginForm.invalid){
      return
    }
    this.spinner.show();
    this.isLoginSubmitted = false;

    this.httpService.login(this.loginForm.value).subscribe((data:any) =>{
      debugger
      this.spinner.hide();
      if(data.status!=AppConstants.FAILED){
        var res:any = JSON.stringify(data);
        this.cookie.set("usercookies",res);

        sessionStorage.setItem('auth-token',data.jwtToken);
        this.router.navigateByUrl('/dashboard');
      }else{
        Swal.fire({
          icon:'error',
          text:"Invalid User"
        });
        return
      }
    })
  }

}

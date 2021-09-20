import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { DataTableService } from 'src/app/core/dataTableService';
import { HttpService } from 'src/app/webservices/http.service';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  formDataDetails!: FormGroup;
  stroageData: any ;

  role:string = 'Unknown'
  tableGridData:any[] = [];
  title:string = "Dashboard"
  constructor(private datatable:DataTableService, private cookie:CookieService, private fb:FormBuilder,
    private httpService:HttpService) { }


  ngOnInit(): void {

    this.formDataDetails = this.fb.group({
      firstName:[null],
      lastName:[null],
      email:[null],
      password:[null],
      role:[null],
      status:[null]
    })

    var userDetails = JSON.parse(this.cookie.get('usercookies'));
    this.role = userDetails.user.role[0].roleName;
    
    if(this.role=="ADMIN"){
      this.getAllUsers();
    }else{
      this.stroageData = userDetails.user;
      this.f.firstName.setValue(this.stroageData.firstName);
      this.f.lastName.setValue(this.stroageData.lastName);
      this.f.email.setValue(this.stroageData.email);
      this.f.password.setValue(this.stroageData.password);
      this.f.role.setValue(this.role);
      this.f.status.setValue(this.stroageData.status);
    }
  }
  get f() {return this.formDataDetails.controls };
  getAllUsers(){
    this.httpService.getAllUsers().subscribe((data:any)=> {
      this.datatable.dataTableDestory();
      this.tableGridData = data.responseDto;
      this.datatable.dataTableReinitalize();
    })
  }
  navigation(val:any){

  }

}

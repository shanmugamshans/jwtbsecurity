import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { AppConstants } from 'src/app/commonutils/AppConstants';
import { HttpService } from 'src/app/webservices/http.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-addnewuser',
  templateUrl: './addnewuser.component.html',
  styleUrls: ['./addnewuser.component.css']
})
export class AddnewuserComponent implements OnInit {

  formDataDetails!: FormGroup;
  title: string = "Add New User"
  
  stroageData: any [] = [];
  id:any = null;

  constructor(private fb:FormBuilder, private router: Router, 
    private activeRouter: ActivatedRoute, private httpservice:HttpService) { }

  ngOnInit(): void {

    this.formDataDetails = this.fb.group({
      firstName:[null,Validators.required],
      lastName:[null],
      email:[null, [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      password:[null,Validators.required],
      role:[null,Validators.required],
      status:[null,Validators.required]
    })

    this.activeRouter.paramMap.subscribe((params: ParamMap) =>{
      this.id = params.get('id');

      if(null != this.id){
        this.title = "Edit User"

        this.valuebinding(this.id);
      }
    })
    

  }

  get login_form_validate() { return this.formDataDetails.controls; }

  formDataSubmit(){
    
    if(this.formDataDetails.valid){
      this.httpservice.addNewUser(this.formDataDetails.value).subscribe((data:any) => {
        if(data.status==AppConstants.SUCCESS){
          Swal.fire({
            icon:'success',
            text:'Add New User Successfully'
          })
        }else{
          Swal.fire({
            icon:'error',
            text:data.errMsg
          })
        }
      })
    }

  }
  valuebinding(val:any){

  }

}

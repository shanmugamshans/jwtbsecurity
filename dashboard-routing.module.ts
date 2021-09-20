import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddnewuserComponent } from './addnewuser/addnewuser.component';
import { DashboardComponent } from './dashboard.component';
import { UserlistComponent } from './userlist/userlist.component';

const routes: Routes = [
  {
    path:'', component:DashboardComponent,
    children:[
      {
        path:'', component:UserlistComponent
      },
      {
        path:'addnewuser', component:AddnewuserComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }

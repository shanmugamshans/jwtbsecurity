import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { UserlistComponent } from './userlist/userlist.component';
import { AddnewuserComponent } from './addnewuser/addnewuser.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DataTablesModule } from 'angular-datatables';


@NgModule({
  declarations: [
    UserlistComponent, 
    AddnewuserComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    ReactiveFormsModule,
    DataTablesModule
  ]
})
export class DashboardModule { }

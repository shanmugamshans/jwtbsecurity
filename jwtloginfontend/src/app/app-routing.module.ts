import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './core/auth.guard';

const routes: Routes = [
  {
    path:'login', component:LoginComponent, canActivate:[AuthGuard]
  },
  {
    path:'', redirectTo:'login', pathMatch:'full'
  },
  {
    path:'dashboard', loadChildren: () => import('../app/components/dashboard/dashboard.module').then(cm => cm.DashboardModule), canActivate:[AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

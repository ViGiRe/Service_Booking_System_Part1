import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupClientComponent } from './basic/component/signup-client/signup-client.component';
import { SignupCompanyComponent } from './basic/component/signup-company/signup-company.component';
import { LoginComponent } from './basic/component/login/login.component';
import { SignupComponent } from './basic/component/signup/signup.component';

const routes: Routes = [
  { path: 'Company', loadChildren: () => import('./company/company.module').then(m => m.CompanyModule) },
  { path: 'Client', loadChildren: () => import('./client/client.module').then(m => m.ClientModule) },
  { path: 'login',component: LoginComponent },
  { path: 'register',component: SignupComponent },
  { path: 'register-client', component: SignupClientComponent},
  { path: 'register-company', component: SignupCompanyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

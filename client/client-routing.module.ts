import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './client.component';
import { CompanyDashboardComponent } from '../company/Pages/company-dashboard/company-dashboard.component';

const routes: Routes = [
  { path: '', component: ClientComponent },
  { path: 'dashboard', component: CompanyDashboardComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }

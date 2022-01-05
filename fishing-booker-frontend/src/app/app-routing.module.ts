import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { CottagesComponent } from './cottages/cottages.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';
import { SpecialRegistrationComponent } from './special-registration/special-registration.component';

const routes: Routes = [
  { path: 'adventures', component: AdventuresComponent},
  { path: 'adventure/:id', component:AdventureDetailComponent},
  { path: 'cottages', component: CottagesComponent},
  { path: 'cottage/:id', component: CottageDetailComponent},
  { path: 'register', component: RegistrationComponent},
  { path: 'login', component: LoginComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'special-register', component: SpecialRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { CottageEditComponent } from './cottage-edit/cottage-edit.component';
import { CottagesComponent } from './cottages/cottages.component';
import { LoginComponent } from './login/login.component';
import { OwnedCottagesComponent } from './owned-cottages/owned-cottages.component';
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
  { path: 'special-register', component: SpecialRegistrationComponent},
  { path: 'owned-cottages', component: OwnedCottagesComponent},
  { path: 'cottage-edit/:id', component: CottageEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

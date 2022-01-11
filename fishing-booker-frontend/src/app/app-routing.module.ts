import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { BoatEditComponent } from './boat-edit/boat-edit.component';
import { BoatsComponent } from './boats/boats.component';
import { ClientBoatReservationsComponent } from './client/client-boat-reservations/client-boat-reservations.component';
import { ClientCottageReservationsComponent } from './client/client-cottage-reservations/client-cottage-reservations.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { CottageEditComponent } from './cottage-edit/cottage-edit.component';
import { CottagesComponent } from './cottages/cottages.component';
import { LoginComponent } from './login/login.component';
import { OwnedBoatsComponent } from './owned-boats/owned-boats.component';
import { OwnedCottagesComponent } from './owned-cottages/owned-cottages.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterAdminComponent } from './register-admin/register-admin.component';
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
  { path: 'boats', component: BoatsComponent},
  { path: 'boat/:id', component: BoatDetailComponent},
  { path: 'owned-cottages', component: OwnedCottagesComponent},
  { path: 'cottage-edit/:id', component: CottageEditComponent},
  { path: 'client-cottage-reservations', component: ClientCottageReservationsComponent},
  { path: 'client-boat-reservations', component: ClientBoatReservationsComponent},
  { path: 'owned-boats', component: OwnedBoatsComponent},
  { path: 'boat-edit/:id', component: BoatEditComponent},
  { path: 'register-admin', component: RegisterAdminComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

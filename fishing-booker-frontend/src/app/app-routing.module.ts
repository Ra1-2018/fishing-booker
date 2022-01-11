import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { BoatEditComponent } from './boat-edit/boat-edit.component';
import { BoatOwnerDetailComponent } from './boat-owner-detail/boat-owner-detail.component';
import { BoatOwnersComponent } from './boat-owners/boat-owners.component';
import { BoatsComponent } from './boats/boats.component';
import { ClientDetailComponent } from './client-detail/client-detail.component';
import { ClientBoatReservationsComponent } from './client/client-boat-reservations/client-boat-reservations.component';
import { ClientCottageReservationsComponent } from './client/client-cottage-reservations/client-cottage-reservations.component';
import { ClientsComponent } from './clients/clients.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { CottageEditComponent } from './cottage-edit/cottage-edit.component';
import { CottageOwnerDetailComponent } from './cottage-owner-detail/cottage-owner-detail.component';
import { CottageOwnersComponent } from './cottage-owners/cottage-owners.component';
import { CottagesComponent } from './cottages/cottages.component';
import { InstructorDetailComponent } from './instructor-detail/instructor-detail.component';
import { InstructorsComponent } from './instructors/instructors.component';
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
  { path: 'register-admin', component: RegisterAdminComponent},
  { path: 'cottage-owners', component: CottageOwnersComponent},
  { path: 'boat-owners', component: BoatOwnersComponent},
  { path: 'instructors', component: InstructorsComponent},
  { path: 'clients', component: ClientsComponent},
  { path: 'cottage-owner-detail/:id', component: CottageOwnerDetailComponent},
  { path: 'boat-owner-detail/:id', component: BoatOwnerDetailComponent},
  { path: 'instructor-detail/:id', component: InstructorDetailComponent},
  { path: 'client-detail/:id', component: ClientDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

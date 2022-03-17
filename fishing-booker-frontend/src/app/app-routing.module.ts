import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { BoatEditComponent } from './boat-edit/boat-edit.component';
import { BoatsComponent } from './boats/boats.component';
import { ClientActionsComponent } from './client/client-actions/client-actions.component';
import { ClientAdventureReservationsComponent } from './client/client-adventure-reservations/client-adventure-reservations.component';
import { ClientBoatReservationsComponent } from './client/client-boat-reservations/client-boat-reservations.component';
import { ClientComplaintComponent } from './client/client-complaint/client-complaint.component';
import { ClientCottageReservationsComponent } from './client/client-cottage-reservations/client-cottage-reservations.component';
import { ClientOrdinaryReservationComponent } from './client/client-ordinary-reservation/client-ordinary-reservation.component';
import { ClientReviewComponent } from './client/client-review/client-review.component';
import { ClientSubscriptionsComponent } from './client/client-subscriptions/client-subscriptions.component';
import { ClientUpcomingReservationsComponent } from './client/client-upcoming-reservations/client-upcoming-reservations.component';
import { CottageDetailOwnerComponent } from './cottage-detail-owner/cottage-detail-owner.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { CottageEditComponent } from './cottage-edit/cottage-edit.component';
import { CottagesComponent } from './cottages/cottages.component';
import { LoginComponent } from './login/login.component';
import { OwnedBoatsComponent } from './owned-boats/owned-boats.component';
import { OwnedCottagesComponent } from './owned-cottages/owned-cottages.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';
import { SpecialRegistrationComponent } from './special-registration/special-registration.component';
import { ReservationHistoryComponent } from './reservation-history/reservation-history.component';
import { ReservationClientComponent } from './reservation-client/reservation-client.component';
import { BoatDetailOwnerComponent } from './boat-detail-owner/boat-detail-owner.component';

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
  { path: 'client-adventure-reservations', component: ClientAdventureReservationsComponent},
  { path: 'client-upcoming-reservations', component: ClientUpcomingReservationsComponent},
  { path: 'owned-boats', component: OwnedBoatsComponent},
  { path: 'boat-edit/:id', component: BoatEditComponent},
  { path: 'cottage-detail-owner/:id', component: CottageDetailOwnerComponent},
  { path: 'client-ordinary-reservation', component: ClientOrdinaryReservationComponent},
  { path: 'actions/:id', component: ClientActionsComponent},
  { path: 'client-subscriptions', component: ClientSubscriptionsComponent},
  { path: 'client-complaint', component: ClientComplaintComponent},
  { path: 'reservation-history', component: ReservationHistoryComponent},
  { path: 'reservation-client/:id', component: ReservationClientComponent},
  { path: 'client-review', component: ClientReviewComponent},
  { path: 'boat-detail-owner/:id', component: BoatDetailOwnerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

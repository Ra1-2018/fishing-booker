import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AdventuresComponent } from './adventures/adventures.component';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AdventureDetailComponent } from './adventure-detail/adventure-detail.component';
import { CottagesComponent } from './cottages/cottages.component';
import { CottageDetailComponent } from './cottage-detail/cottage-detail.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { SpecialRegistrationComponent } from './special-registration/special-registration.component';
import { BoatsComponent } from './boats/boats.component';
import { BoatDetailComponent } from './boat-detail/boat-detail.component';
import { OwnedCottagesComponent } from './owned-cottages/owned-cottages.component';
import { CottageEditComponent } from './cottage-edit/cottage-edit.component';
import { TokenInterceptor } from './interceptor/tokenInterceptor';
import { ClientCottageReservationsComponent } from './client/client-cottage-reservations/client-cottage-reservations.component';
import { ClientBoatReservationsComponent } from './client/client-boat-reservations/client-boat-reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    AdventuresComponent,
    AdventureDetailComponent,
    CottagesComponent,
    CottageDetailComponent,
    RegistrationComponent,
    LoginComponent,
    ProfileComponent,
    SpecialRegistrationComponent,
    BoatsComponent,
    BoatDetailComponent,
    OwnedCottagesComponent,
    CottageEditComponent,
    ClientCottageReservationsComponent,
    ClientBoatReservationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
